package sprint5.peoplepegistration.cep.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.repository.CepRepository;
import sprint5.peoplepegistration.configuration.webClient.cep.IntegrationCepClient;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.model.request.PersonRequest;
import sprint5.peoplepegistration.people.model.response.PersonResponse;

@Service
@AllArgsConstructor
public class CepService {
    private final IntegrationCepClient integration;
    private final CepRepository cepRepository;

    public Mono<CepEntity> findByCep(String cep) {
        return integration.findCep(cep);
    }

    public Mono<PersonEntity> pesquisarCepESalvarNoBanco(PersonEntity pessoa) {
        return findByCep(pessoa.getCepEntity().getCep())
                .map((CepEntity cepEntity) -> {
                    pessoa.setCepEntity(cepEntity);
                    return pessoa;
                });
    }
}
