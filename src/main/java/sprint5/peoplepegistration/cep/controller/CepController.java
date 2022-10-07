package sprint5.peoplepegistration.cep.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;

import javax.validation.constraints.NotNull;

import static reactor.core.publisher.Mono.error;

@RestController
@RequestMapping("api/v1/cep")
@AllArgsConstructor
public class CepController {
    private final CepService cepService;

    @GetMapping("{cep}")
    public Mono<CepEntity> findCep(@PathVariable @NotNull String cep) {
        return cepService.findByCep(cep);
    }
}
