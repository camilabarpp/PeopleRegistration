package sprint5.peoplepegistration.cep.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;
import sprint5.peoplepegistration.configuration.webClient.cep.IntegrationCepClient;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

import static reactor.core.publisher.Mono.error;

@Slf4j
@Service
@AllArgsConstructor
public class CepService {
    private final IntegrationCepClient integration;

    public Mono<CepEntity> findCep(String cep) {
        return integration.findCep(cep)
                .switchIfEmpty(error(new CepNotFoundException("Sorry, CEP not found!")))
                .doOnError(error -> log.error("Sorry, CEP {} not found!", cep));
    }


    public Mono<PersonEntity> searchCepAndSavaToDataBase(PersonEntity pessoa) {
        String cep = pessoa.getCepEntity().getCep();
        return findCep(cep)
                .map((CepEntity cepEntity) -> {
                    pessoa.setCepEntity(cepEntity);
                    return pessoa;
                })
                .switchIfEmpty(error(new CepNotFoundException("Sorry, CEP not found!")))
                .doOnError(error -> log.error("Sorry, CEP not found!"));
    }
}
