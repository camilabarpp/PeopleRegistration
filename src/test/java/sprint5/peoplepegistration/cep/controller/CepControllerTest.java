package sprint5.peoplepegistration.cep.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.controller.facade.CepControllerFacade;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static sprint5.peoplepegistration.cep.model.mapper.CepMapper.responseToEntity;
import static sprint5.peoplepegistration.cep.service.CepServiceStub.createAEntityCep;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CepController.class, CepControllerFacade.class})
class CepControllerTest {

    @InjectMocks
    CepController cepController;

    @Mock
    CepControllerFacade cepControllerFacade;

    @Test
    @DisplayName("Should find a CEP with success")
    void shouldFindCepWithSuccess() {
        String cep = "94020070";
        CepEntity entity = createAEntityCep();

        when(cepControllerFacade.findCep(cep))
                .thenReturn(Mono.just(responseToEntity(entity)));

        var response = cepController.findCep(cep).block();

        assertNotNull(response);
        assertEquals(entity.getCep(), response.getCep());
        assertEquals(entity.getLogradouro(), response.getLogradouro());
        assertEquals(entity.getBairro(), response.getBairro());
        assertEquals(entity.getLocalidade(), response.getLocalidade());
        assertEquals(entity.getUf(), response.getUf());
    }

    @Test
    @DisplayName("Should throws CepNotFoundException when do not found a CEP")
    void shouldFindCepWithSuccess2() {
        String cep = "12345";

        doThrow(CepNotFoundException.class)
                .when(cepControllerFacade).findCep(cep);

        assertThrows(CepNotFoundException.class, () -> cepController.findCep(cep));
    }
}