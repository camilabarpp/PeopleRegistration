package sprint5.peoplepegistration.people.controller;

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
import sprint5.peoplepegistration.people.controller.facade.PeopleControllerFacade;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.model.request.PersonRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntity;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createARequest;
import static sprint5.peoplepegistration.people.model.mapper.PersonMapper.pessoaResponse;
import static sprint5.peoplepegistration.people.model.mapper.PersonMapper.requestPessoa;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PeopleController.class, PeopleControllerFacade.class})
class PeoplecontrollerTest {

    @InjectMocks
    PeopleController peoplecontroller;

    @Mock
    PeopleControllerFacade controllerFacade;

    @Test
    @DisplayName("Should find all people with sucess")
    void shouldFindAllPeopleWithSuccess() {
        var expected = createAEntity();

        when(controllerFacade.findAll()).thenReturn(Flux.just(expected));

        var actual = peoplecontroller.findAll().blockFirst();

        assertNotNull(actual);
        assertEquals(expected.getNome(), actual.getNome());
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find all people and it is empty")
    void shouldThrowsApiNotFoundWhenTryFindAllAndItIsEmpty() {
        when(controllerFacade.findAll()).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peoplecontroller.findAll());
    }

    @Test
    @DisplayName("Should find a person by ID with success")
    void shouldFindAPersonByIDWithSuccess() {
        String id = "1";
        PersonEntity expect = createAEntity();

        when(controllerFacade.findByID(id)).thenReturn(Mono.just(expect));

        var actual = peoplecontroller.findById(id).block();

        assertNotNull(actual);
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getNome(), actual.getNome());
        assertEquals(expect.getDataDeNascimento(), actual.getDataDeNascimento());
        assertEquals(expect.getCepEntity(), actual.getCepEntity());

        verify(controllerFacade, atLeastOnce()).findByID(id);
    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid ID")
    void shouldThrowsApiNotFoundWhenTryFindAInvalidID() {
        String id = "2";

        when(controllerFacade.findByID(id)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peoplecontroller.findById(id));
    }

    @Test
    @DisplayName("Should find by name with success")
    void shouldShowAllPeopleByName() {
        PersonEntity personEntity = createAEntity();
        List<PersonEntity> names = Collections.singletonList(personEntity);

        when(controllerFacade.findByName(names.toString()))
                .thenReturn(Flux.just(personEntity));

        var actual = peoplecontroller.findByName(names.toString()).blockFirst();

        assertEquals(pessoaResponse(personEntity), actual);
        verify(controllerFacade, atLeastOnce()).findByName(names.toString());

    }

    @Test
    @DisplayName("Should throws ApiNotFound when try find a invalid name")
    void shouldThrownsApiNotFoundExceptionWhenTryFindAPersonWithInvalidName() {
        String name = "Eduarda Lima";

        when(controllerFacade.findByName(name)).thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class,() -> peoplecontroller.findByName(name));
    }

    @Test
    @DisplayName("Should create a person with success")
    void shouldRegisterAPersonWithSuccess() {
        PersonEntity personEntity = createAEntity();
        PersonRequest personRequest = createARequest();

        when(controllerFacade.create(any()))
                .thenReturn(Mono.just(personEntity));

        var response = peoplecontroller.create(personRequest).block();

        assertNotNull(response);
        assertEquals(personEntity.getId(), response.getId());
        assertEquals(personEntity.getNome(), response.getNome());
        assertEquals(personEntity.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(personEntity.getCepEntity(), response.getCepEntity());

        verify(controllerFacade,  atLeastOnce()).create(requestPessoa(personRequest));
    }

    @Test
    @DisplayName("Should not create a null person and throws WebExchangeBindException")
    void shouldNotCreateANullPerson() {
        PersonEntity personEntity = new PersonEntity();
        PersonRequest personRequest = new PersonRequest();

        when(controllerFacade.create(personEntity)).thenThrow(WebExchangeBindException.class);

        assertThrows(WebExchangeBindException.class, () -> peoplecontroller.create(personRequest));
    }

    @Test
    @DisplayName("Should update a person by ID with success")
    void shouldChangeAPersonByIDWithSucess() {
        String id = "1";
        PersonEntity pessoa = createAEntity();
        PersonRequest request = createARequest();

        when(controllerFacade.update(any(),any())).thenReturn(Mono.just(pessoa));

        var response = peoplecontroller.update(id, request).block();

        assertNotNull(response);
        assertEquals(pessoa.getId(), response.getId());
        assertEquals(pessoa.getNome(), response.getNome());
        assertEquals(pessoa.getDataDeNascimento(), response.getDataDeNascimento());
        assertEquals(pessoa.getCepEntity(), response.getCepEntity());

        verify(controllerFacade,  atLeastOnce()).update(id, requestPessoa(request));
    }

    @Test
    @DisplayName("Should not update a person with invalid ID")
    void shouldNotUpdatePersonWhenIdIsNull() {
        PersonRequest personRequest = createARequest();

        when(controllerFacade.update(any(), any(PersonEntity.class)))
                .thenThrow(ApiNotFoundException.class);

        assertThrows(ApiNotFoundException.class, () -> peoplecontroller.update("2", personRequest));
    }

    @Test
    @DisplayName("Should delete one or a list of ids with success")
    void shouldDeletePeopleByIDs() {
        List<String> ids = new ArrayList<>();

        when(controllerFacade.deleteAllByIDs(ids)).thenReturn(Mono.empty());

        peoplecontroller.deleteAll(ids);

        verify(controllerFacade, atLeastOnce()).deleteAllByIDs(ids);
    }
}