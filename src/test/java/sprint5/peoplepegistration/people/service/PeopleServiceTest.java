package sprint5.peoplepegistration.people.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.service.facade.CepServiceFacade;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.model.mapper.PersonMapper;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntity;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PeopleServiceTest {
    @InjectMocks
    PeopleService peopleService;

    @Mock
    PeopleRepository peopleRepository;

    @Mock
    CepServiceFacade cepServiceFacade;

    @Mock
    PersonEntity personEntity = createAEntity();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should find all people with sucess")
    void shouldFindAllPeopleWithSuccess() {
        var expected = createAEntity();

        when(peopleRepository.findAll()).thenReturn(Flux.just(expected));

        var actual = peopleService.findAll().blockFirst();

        assertNotNull(actual);
        assertEquals(expected.getNome(), actual.getNome());
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find all people and it is empty")
    void shouldThrowsApiNotFoundWhenTryFindAllAndItIsEmpty() {
        when(peopleRepository.findAll()).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peopleService.findAll());
    }

    @Test
    @DisplayName("Should find a person by ID with success")
    void shouldFindAPersonByIDWithSuccess() {
        String id = "1";
        PersonEntity expect = createAEntity();

        when(peopleRepository.findById(id)).thenReturn(Mono.just(expect));

        var actual = peopleService.findById(id).block();

        assertNotNull(actual);
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getCepEntity(), actual.getCepEntity());

        verify(peopleRepository, atLeastOnce()).findById(id);
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid ID")
    void shouldThrowsApiNotFoundWhenTryFindAInvalidID() {
        String id = "2";

        when(peopleRepository.findById(id)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peopleService.findById(id));
    }

    @Test
    @DisplayName("Should find by name with success")
    void shouldShowAllPeopleByName() {
        PersonEntity personEntity = createAEntity();
        List<PersonEntity> names = Collections.singletonList(personEntity);

        when(peopleRepository.findByNome(names.toString()))
                .thenReturn(Flux.just(personEntity));

        var actual = peopleService.findByName(names.toString()).blockFirst();

        assertEquals(personEntity, actual);
        verify(peopleRepository, atLeastOnce()).findByNome(names.toString());

    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid name")
    void shouldThrownsApiNotFoundExceptionWhenTryFindAPersonWithInvalidName() {
        String name = "Eduarda Lima";

        when(peopleRepository.findByNome(name)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class,() -> peopleService.findByName(name));
    }

    @Test
    @DisplayName("Should create a person with success")
    void shouldRegisterAPersonWithSuccess() {
        when(peopleRepository.save(personEntity)).thenReturn(Mono.just(personEntity));
        when(cepServiceFacade.searchCepAndSavaToDataBase(personEntity)).thenReturn(Mono.just(personEntity));

        var actual = peopleService.create(personEntity).block();
        cepServiceFacade.searchCepAndSavaToDataBase(personEntity);

        assertNotNull(actual);
        assertEquals(personEntity.getId(), actual.getId());
        assertEquals(personEntity.getNome(), actual.getNome());
        assertEquals(personEntity.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(personEntity.getCepEntity(), actual.getCepEntity());
        verify(peopleRepository, atLeastOnce()).save(personEntity);
    }

    @Test
    @DisplayName("Should not create a null person and throws NullPointerException")
    void shouldNotCreateANullPerson() {
        PersonEntity person = new PersonEntity();

        when(peopleRepository.save(person)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> peopleService.create(person));
    }

    @Test
    @DisplayName("Should update a person by ID with success")
    void shouldChangeAPersonByIDWithSucess() {
        String id = "1";
        PersonEntity atualizada = createAEntity();

        when(peopleRepository.findById(id)).thenReturn(Mono.just(atualizada));
        when(peopleRepository.save(any(PersonEntity.class))).thenReturn(Mono.just(atualizada));
        atualizada.setId(id);

        var actual = peopleService.update(id, atualizada);

        assertNotNull(actual);
        verify(peopleRepository, atLeastOnce()).findById(id);
    }

    @Test
    @DisplayName("Should not update a person with invalid ID")
    void shouldNotUpdatePersonWhenIdIsNull() {
        PersonEntity person = new PersonEntity();

        when(peopleRepository.insert(person)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> peopleService.update("10", person));
        verify(peopleRepository, never()).save(person);
    }

    @Test
    @DisplayName("Should delete one or a list of ids with success")
    void shouldDeletePeopleByIDs() {
        List<String> ids = new ArrayList<>();

        when(peopleRepository.deleteAllById(ids)).thenReturn(Mono.empty());

        peopleService.deletePeolpleByIDs(ids);

        verify(peopleRepository, atLeastOnce()).deleteAllById(ids);
    }

    @Test
    @DisplayName("Should delete all people when list is empty")
    void shouldDeteAllPeopleWhenIdIsNull2() {
        when(peopleRepository.deleteAll()).thenReturn(Mono.empty());

        peopleService.deletePeolpleByIDs(null);

        verify(peopleRepository, times(1)).deleteAll();
    }

    @Test
    @DisplayName("Test coverage in Person Mapper class")
    void testCoverageInPersonMapper() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = PersonMapper.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}