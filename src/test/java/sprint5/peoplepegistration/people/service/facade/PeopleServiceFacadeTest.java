package sprint5.peoplepegistration.people.service.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.service.PeopleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntity;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PeopleServiceFacade.class, PeopleService.class})
class PeopleServiceFacadeTest {

    @InjectMocks
    PeopleServiceFacade peopleServiceFacade;

    @Mock
    PeopleService peopleService;

    @Mock
    PersonEntity personEntity = createAEntity();

    @Test
    @DisplayName("Should find all people with sucess")
    void shouldFindAllPeopleWithSuccess() {
        var expected = createAEntity();

        when(peopleService.findAll()).thenReturn(Flux.just(expected));

        var actual = peopleServiceFacade.findAll().blockFirst();

        assertNotNull(actual);
        assertEquals(expected.getNome(), actual.getNome());
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find all people and it is empty")
    void shouldThrowsApiNotFoundWhenTryFindAllAndItIsEmpty() {
        when(peopleService.findAll()).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peopleServiceFacade.findAll());
    }

    @Test
    @DisplayName("Should find a person by ID with success")
    void shouldFindAPersonByIDWithSuccess() {
        String id = "1";
        PersonEntity expect = createAEntity();

        when(peopleService.findById(id)).thenReturn(Mono.just(expect));

        var actual = peopleServiceFacade.findById(id).block();

        assertNotNull(actual);
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getCepEntity(), actual.getCepEntity());

        verify(peopleService, atLeastOnce()).findById(id);
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid ID")
    void shouldThrowsApiNotFoundWhenTryFindAInvalidID() {
        String id = "2";

        when(peopleService.findById(id)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peopleServiceFacade.findById(id));
    }

    @Test
    @DisplayName("Should find by name with success")
    void shouldShowAllPeopleByName() {
        PersonEntity personEntity = createAEntity();
        List<PersonEntity> names = Collections.singletonList(personEntity);

        when(peopleService.findByName(names.toString()))
                .thenReturn(Flux.just(personEntity));

        var actual = peopleServiceFacade.findByName(names.toString()).blockFirst();

        assertEquals(personEntity, actual);
        verify(peopleService, atLeastOnce()).findByName(names.toString());

    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid name")
    void shouldThrownsApiNotFoundExceptionWhenTryFindAPersonWithInvalidName() {
        String name = "Eduarda Lima";

        when(peopleService.findByName(name)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class,() -> peopleServiceFacade.findByName(name));
    }

    @Test
    @DisplayName("Should create a person with success")
    void shouldRegisterAPersonWithSuccess() {
        when(peopleService.create(personEntity)).thenReturn(Mono.just(personEntity));
        //when(cepServiceFacade.searchCepAndSavaToDataBase(personEntity)).thenReturn(Mono.just(personEntity));

        var actual = peopleServiceFacade.create(personEntity).block();
        //cepServiceFacade.searchCepAndSavaToDataBase(personEntity);

        assertNotNull(actual);
        assertEquals(personEntity.getId(), actual.getId());
        assertEquals(personEntity.getNome(), actual.getNome());
        assertEquals(personEntity.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(personEntity.getCepEntity(), actual.getCepEntity());
        verify(peopleService, atLeastOnce()).create(personEntity);
    }

    @Test
    @DisplayName("Should not create a null person and throws NullPointerException")
    void shouldNotCreateANullPerson() {
        PersonEntity person = new PersonEntity();

        when(peopleService.create(person)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> peopleServiceFacade.create(person));
    }

    @Test
    @DisplayName("Should update a person by ID with success")
    void shouldChangeAPersonByIDWithSucess() {
        String id = "1";
        PersonEntity atualizada = createAEntity();

        when(peopleService.update(any(), any(PersonEntity.class))).thenReturn(Mono.just(atualizada));

        var actual = peopleServiceFacade.update(id, atualizada).block();

        assertNotNull(actual);
        assertEquals(atualizada.getId(), actual.getId());
        assertEquals(atualizada.getNome(), actual.getNome());
        assertEquals(atualizada.getDataDeNascimento(), actual.getDataDeNascimento());
    }

    @Test
    @DisplayName("Should not update a person with invalid ID")
    void shouldNotUpdatePersonWhenIdIsNull() {
        PersonEntity person = new PersonEntity();

        when(peopleService.update(any(), any(PersonEntity.class))).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> peopleServiceFacade.update("10", person));
    }

    @Test
    @DisplayName("Should delete one or a list of ids with success")
    void shouldDeletePeopleByIDs() {
        List<String> ids = new ArrayList<>();

        when(peopleService.deletePeolpleByIDs(ids)).thenReturn(Mono.empty());

        peopleServiceFacade.deleteAllByIds(ids);

        verify(peopleService, atLeastOnce()).deletePeolpleByIDs(ids);
    }

    @Test
    @DisplayName("Should delete all people when list is empty")
    void shouldDeteAllPeopleWhenIdIsNull2() {
        when(peopleService.deletePeolpleByIDs(null)).thenReturn(Mono.empty());

        peopleServiceFacade.deleteAllByIds(null);

        verify(peopleService, times(1)).deletePeolpleByIDs(null);
    }
}