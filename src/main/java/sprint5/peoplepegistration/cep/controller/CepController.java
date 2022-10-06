package sprint5.peoplepegistration.cep.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.people.service.PeopleService;

@RestController
@RequestMapping("api/v1/cep")
@AllArgsConstructor
public class CepController {

    private final CepService cepService;

    @GetMapping("{cep}") //Está funcionando
    public Mono<CepEntity> findCep(@PathVariable String cep) {
        return cepService.findByCep(cep);
    }

    @GetMapping //Estou testando
    public Mono<CepEntity> findCep2(@RequestBody CepEntity cepEntity) {
        return cepService.findByCep(cepEntity.getCep());
    }
}
