package sprint5.peoplepegistration.cep.controller.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.mapper.CepMapper;
import sprint5.peoplepegistration.cep.model.response.CepResponse;
import sprint5.peoplepegistration.cep.service.facade.CepServiceFacade;

import javax.validation.constraints.NotNull;

@Component
@AllArgsConstructor
public class CepControllerFacade {
    private final CepServiceFacade cepServiceFacade;

    public Mono<CepResponse> findCep(@PathVariable @NotNull String cep) {
        return cepServiceFacade.findByCep(cep)
                .map(CepMapper::responseToEntity);
    }

}
