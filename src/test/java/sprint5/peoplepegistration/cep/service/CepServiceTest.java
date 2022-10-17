package sprint5.peoplepegistration.cep.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.model.mapper.CepMapper;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;
import sprint5.peoplepegistration.configuration.webClient.cep.IntegrationCepClient;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntity;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntityCep;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CepService.class, IntegrationCepClient.class})
class CepServiceTest {

    @InjectMocks
    CepService cepService;

    @Mock
    IntegrationCepClient integrationCepClient;

    @Mock
    PersonEntity personEntity = createAEntity();

    @Test
    @DisplayName("Should find CEP with success")
    void shouldFindByCepWithSuccess() {
        CepEntity expected = createAEntityCep();
        when(integrationCepClient.findCep("94020070"))
                .thenReturn(Mono.just(expected));

        var actual = cepService.findCep("94020070").block();

        assertNotNull(actual);
        assertEquals(expected.getCep(), actual.getCep());
        assertEquals(expected.getLogradouro(), actual.getLogradouro());
        assertEquals(expected.getBairro(), actual.getBairro());
    }

    @Test
    @DisplayName("Should throws CepNotFoundException when do not find CEP")
    void shouldThrowsCepNotFoundExceptionWhenDoNotFoundCep() {
        when(integrationCepClient.findCep("12345"))
                .thenThrow(CepNotFoundException.class);

        assertThrows(CepNotFoundException.class, () -> cepService.findCep("12345"));
    }

    @Test
    @DisplayName("Shold find CEP with sucess and save to data base")
    void shouldSearchCepAndSavaToDataBaseWithSuccess() {
        PersonEntity personEntity = createAEntity();
        String cep = "94020070";
        CepEntity cepEntity = createAEntityCep();

        when(integrationCepClient.findCep(cep))
                .thenReturn(Mono.just(cepEntity));
        personEntity.setCepEntity(cepEntity);

        var actual = cepService.searchCepAndSavaToDataBase(personEntity);

        assertNotNull(actual);
    }

    @Test
    @DisplayName("Should throws CepNotFoundException when do not find CEP 2")
    void shouldThrowsCepNotFoundExceptionWhenDoNotFoundCep2() {
        when(integrationCepClient.findCep("12345"))
                .thenThrow(CepNotFoundException.class);

        assertThrows(CepNotFoundException.class, () -> cepService.findCep("12345"));
    }

    @Test
    @DisplayName("Test coverage in Cep Mapper class")
    void testCoverageInPersonMapper() {
        assertThrows(InvocationTargetException.class, () -> {
            var constructor = CepMapper.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}