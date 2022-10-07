package sprint5.peoplepegistration.cep.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.controller.facade.CepControllerFacade;
import sprint5.peoplepegistration.cep.model.response.CepResponse;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/cep")
@AllArgsConstructor
public class CepController {
    private final CepControllerFacade cepControllerFacade;

    @GetMapping("{cep}")
    public Mono<CepResponse> findCep(@PathVariable @NotNull String cep) {
        return cepControllerFacade.findCep(cep);
    }
}
