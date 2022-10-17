package sprint5.peoplepegistration.people.controller.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.service.facade.PeopleServiceFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static sprint5.peoplepegistration.people.controller.stub.PeopleServiceStub.createAEntity;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PeopleControllerFacade.class, PeopleServiceFacade.class})
class PeopleControllerFacadeTest {
    @InjectMocks
    PeopleControllerFacade controllerFacade;

    @Mock
    PeopleServiceFacade serviceFacade;

    @Test
    @DisplayName("Should find all people with sucess")
    void shouldFindAllPeopleWithSuccess() {
        var expected = createAEntity();

        when(serviceFacade.findAll()).thenReturn(Flux.just(expected));

        var actual = controllerFacade.findAll().blockFirst();

        assertNotNull(actual);
        assertEquals(expected.getNome(), actual.getNome());
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find all people and it is empty")
    void shouldThrowsApiNotFoundWhenTryFindAllAndItIsEmpty() {
        when(serviceFacade.findAll()).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> controllerFacade.findAll());
    }

    @Test
    @DisplayName("Should find a person by ID with success")
    void shouldFindAPersonByIDWithSuccess() {
        String id = "1";
        PersonEntity expect = createAEntity();

        when(serviceFacade.findById(id)).thenReturn(Mono.just(expect));

        var actual = controllerFacade.findByID(id).block();

        assertNotNull(actual);
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getCepEntity(), actual.getCepEntity());

        verify(serviceFacade, atLeastOnce()).findById(id);
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid ID")
    void shouldThrowsApiNotFoundWhenTryFindAInvalidID() {
        String id = "2";

        when(serviceFacade.findById(id)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> controllerFacade.findByID(id));
    }

    @Test
    @DisplayName("Should find by name with success")
    void shouldShowAllPeopleByName() {
        PersonEntity personEntity = createAEntity();
        List<PersonEntity> names = Collections.singletonList(personEntity);

        when(serviceFacade.findByName(names.toString()))
                .thenReturn(Flux.just(personEntity));

        var actual = controllerFacade.findByName(names.toString()).blockFirst();

        assertEquals(personEntity, actual);
        verify(serviceFacade, atLeastOnce()).findByName(names.toString());

    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid name")
    void shouldThrownsApiNotFoundExceptionWhenTryFindAPersonWithInvalidName() {
        String name = "Eduarda Lima";

        when(serviceFacade.findByName(name)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class,() -> controllerFacade.findByName(name));
    }

    @Test
    @DisplayName("Should create a person with success")
    void shouldRegisterAPersonWithSuccess() {
        PersonEntity personEntity = createAEntity();

        when(serviceFacade.create(any())).thenReturn(Mono.just(personEntity));

        var response = controllerFacade.create(personEntity).block();

        assertNotNull(response);
        assertEquals(personEntity.getId(), response.getId());
        assertEquals(personEntity.getNome(), response.getNome());
        assertEquals(personEntity.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(personEntity.getCepEntity(), response.getCepEntity());

        verify(serviceFacade,  atLeastOnce()).create(personEntity);
    }

    @Test
    @DisplayName("Should not create a null person and throws WebExchangeBindException")
    void shouldNotCreateANullPerson() {
        PersonEntity personEntity = new PersonEntity();

        when(serviceFacade.create(any())).thenThrow(WebExchangeBindException.class);

        assertThrows(WebExchangeBindException.class, () -> controllerFacade.create(personEntity));
    }

    @Test
    @DisplayName("Should update a person by ID with success")
    void shouldChangeAPersonByIDWithSucess() {
        String id = "1";
        PersonEntity personEntity = createAEntity();

        when(serviceFacade.update(any(),any())).thenReturn(Mono.just(personEntity));

        var response = controllerFacade.update(id, personEntity).block();

        assertNotNull(response);
        assertEquals(personEntity.getId(), response.getId());
        assertEquals(personEntity.getNome(), response.getNome());
        assertEquals(personEntity.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(personEntity.getCepEntity(), response.getCepEntity());

        verify(serviceFacade,  atLeastOnce()).update(id, personEntity);
    }

    @Test
    @DisplayName("Should not update a person with invalid ID")
    void shouldNotUpdatePersonWhenIdIsNull() {
        PersonEntity personEntity = new PersonEntity();

        when(serviceFacade.update(null, personEntity))
                .thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> controllerFacade.update(null, personEntity));
    }

    @Test
    @DisplayName("Should delete one or a list of ids with success")
    void shouldDeletePeopleByIDs() {
        List<String> ids = new ArrayList<>();

        when(serviceFacade.deleteAllByIds(ids)).thenReturn(Mono.empty());

        controllerFacade.deleteAllByIDs(ids);

        verify(serviceFacade, atLeastOnce()).deleteAllByIds(ids);
    }
}