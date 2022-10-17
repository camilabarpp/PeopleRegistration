package sprint5.peoplepegistration.cep.service.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntity;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntityCep;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CepServiceFacade.class, CepService.class})
class CepServiceFacadeTest {

    @InjectMocks
    CepServiceFacade cepServiceFacade;

    @Mock
    CepService cepService;

    @Mock
    PersonEntity personEntity = createAEntity();

    @Test
    @DisplayName("Should find CEP with success")
    void shouldFindByCepWithSuccess() {
        CepEntity expected = createAEntityCep();
        when(cepService.findCep("94020070"))
                .thenReturn(Mono.just(expected));

        var actual = cepServiceFacade.findCep("94020070").block();

        assertNotNull(actual);
        assertEquals(expected.getCep(), actual.getCep());
        assertEquals(expected.getLogradouro(), actual.getLogradouro());
        assertEquals(expected.getBairro(), actual.getBairro());
    }

    @Test
    @DisplayName("Should throws CepNotFoundException when do not find CEP")
    void shouldThrowsCepNotFoundExceptionWhenDoNotFoundCep() {
        when(cepService.findCep("12345"))
                .thenThrow(CepNotFoundException.class);

        assertThrows(CepNotFoundException.class, () -> cepServiceFacade.findCep("12345"));
    }

    @Test
    @DisplayName("Shold find CEP with sucess and save to data base")
    void shouldSearchCepAndSavaToDataBaseWithSuccess() {
        when(cepService.searchCepAndSavaToDataBase(personEntity))
                .thenReturn(Mono.just(personEntity));
        var actual = cepServiceFacade.searchCepAndSavaToDataBase(personEntity).block();

        assertNotNull(actual);
        assertEquals(personEntity.getCepEntity(), actual.getCepEntity());
    }

    @Test
    @DisplayName("Should throws CepNotFoundException when do not find CEP 2")
    void shouldThrowsCepNotFoundExceptionWhenDoNotFoundCep2() {
        when(cepService.findCep("12345"))
                .thenThrow(CepNotFoundException.class);

        assertThrows(CepNotFoundException.class, () -> cepServiceFacade.findCep("12345"));
    }
}