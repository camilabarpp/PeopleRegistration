package sprint5.peoplepegistration.cep.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Component
@AllArgsConstructor
public class CepServiceFacade {

    private final CepService cepService;

    public Mono<CepEntity> findByCep(String cep) {
        return cepService.findByCep(cep);
    }

    public Mono<PersonEntity> searchCepAndSavaToDataBase(PersonEntity personEntity) {
        return cepService.searchCepAndSavaToDataBase(personEntity);
    }
}
