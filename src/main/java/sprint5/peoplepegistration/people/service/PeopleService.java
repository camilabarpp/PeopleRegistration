package sprint5.peoplepegistration.people.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.configuration.webClient.cep.IntegrationCepClient;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

@Service
@AllArgsConstructor
public class PeopleService {
    private PeopleRepository peopleRepository;
    private CepService cepService;
    private IntegrationCepClient integrationCepClient;

    public Flux<PersonEntity> findAll() {
        return peopleRepository.findAll();
    }

    public Mono<PersonEntity> findById(String id) {
        return peopleRepository.findById(id);
    }

    public Mono<PersonEntity> create(PersonEntity personEntity) {
        return cepService.pesquisarCepESalvarNoBanco(personEntity)
                .flatMap(peopleRepository::save);
    }

    public Flux<PersonEntity> update(String id, PersonEntity pessoa) {
        return peopleRepository.findById(id)
                .map((PersonEntity personEntity) -> {
                    pessoa.setId(personEntity.getId());
                    personEntity.setNome(pessoa.getNome());
                    personEntity.setDataDeNascimento(pessoa.getDataDeNascimento());
                    pesquisarCepESalvarNoBanco(pessoa);
                    peopleRepository.save(pessoa);
                    cepService.pesquisarCepESalvarNoBanco(pessoa);
                    return pessoa;
                })
                .concatWith(cepService.pesquisarCepESalvarNoBanco(pessoa));
/*        return cepService.pesquisarCepESalvarNoBanco(pessoa)
                .map((PersonEntity personEntity) -> {
                    var found = peopleRepository.findById(id);
                    pessoa.setId(String.valueOf(found));
                    peopleRepository.save(pessoa);
                    return pessoa;
                })
                .flatMap(peopleRepository::save);*/
    }

    public Mono<PersonEntity> update2(String id, PersonEntity pessoa){
        return peopleRepository.findById(id)
                .map((PersonEntity personEntity) -> {
                    pessoa.setId(personEntity.getId());
                    personEntity.setNome(pessoa.getNome());
                    personEntity.setDataDeNascimento(pessoa.getDataDeNascimento());
                    pesquisarCepESalvarNoBanco(pessoa);
                    peopleRepository.save(pessoa);
                    return pessoa;
                });
    }

    public Mono<PersonEntity> pesquisarCepESalvarNoBanco(PersonEntity pessoa) {
        return integrationCepClient.findCep(pessoa.getCepEntity().getCep())
                .map((CepEntity cepEntity) -> {
                    cepEntity.setBairro(pessoa.getCepEntity().getBairro());
                    cepEntity.setCep(pessoa.getCepEntity().getCep());
                    cepEntity.setLogradouro(pessoa.getCepEntity().getLogradouro());
                    pessoa.setCepEntity(cepEntity);
                    return pessoa;
                });
    }

    public Mono<Void> deleteById(String id) {
        return peopleRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return peopleRepository.deleteAll();
    }

    public Mono<Boolean> existsByNome(PersonEntity personEntity) {
        return peopleRepository.existsByNome(personEntity.getNome());
    }

    public Mono<Boolean> existsByIdAndNome(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndNome(personEntity.getId(), personEntity.getNome());
    }

    public Mono<Boolean> existsByDataDeNascimento(PersonEntity personEntity) {
        return peopleRepository.existsByDataDeNascimento(personEntity.getDataDeNascimento());
    }
}
