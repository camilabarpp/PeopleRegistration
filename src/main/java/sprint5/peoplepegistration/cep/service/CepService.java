package sprint5.peoplepegistration.cep.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;
import sprint5.peoplepegistration.configuration.exception.errorresponse.ErrorResponse;
import sprint5.peoplepegistration.configuration.webClient.cep.IntegrationCepClient;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

import static reactor.core.publisher.Mono.error;

@Service
@AllArgsConstructor
public class CepService {
    private final IntegrationCepClient integration;

    public Mono<CepEntity> findByCep(String cep) {
        return integration.findCep(cep);
    }

    public Mono<PersonEntity> pesquisarCepESalvarNoBanco(PersonEntity pessoa) {
        return findByCep(pessoa.getCepEntity().getCep())
                .map((CepEntity cepEntity) -> {
                    pessoa.setCepEntity(cepEntity);
                    return pessoa;
                })
                .switchIfEmpty(error(new CepNotFoundException("Sorry, CEP not found!")));
    }
}
