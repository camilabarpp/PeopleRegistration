package sprint5.peoplepegistration.people.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

import static reactor.core.publisher.Mono.error;

@Service
@AllArgsConstructor
public class PeopleService {
    private PeopleRepository peopleRepository;
    private CepService cepService;

    public Flux<PersonEntity> findAll() {
        return peopleRepository.findAll();
    }

    public Mono<PersonEntity> findById(String id) {
        return peopleRepository.findById(id)
                .switchIfEmpty(error(new ApiNotFoundException()));
    }

    public Mono<PersonEntity> create(PersonEntity personEntity) {
        return cepService.pesquisarCepESalvarNoBanco(personEntity)
                .flatMap(peopleRepository::save);
    }

    public Mono<Void> deleteById(String id) {
        return peopleRepository.deleteById(id);
    }

    public Mono<PersonEntity> update(String id, PersonEntity personEntity) {
        return peopleRepository.findById(id)
                .map((PersonEntity person) -> {
                    personEntity.setId(id);
                    peopleRepository.save(person);
                    return personEntity;
                })
                .flatMap(cepService::pesquisarCepESalvarNoBanco)
                .flatMap(peopleRepository::save)
                .switchIfEmpty(error(new ApiNotFoundException()));
    }

    public Mono<Void> deleteAll() {
        return peopleRepository.deleteAll();
    }

}
