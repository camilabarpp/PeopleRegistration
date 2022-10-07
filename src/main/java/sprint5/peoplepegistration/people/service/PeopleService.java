package sprint5.peoplepegistration.people.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.service.CepService;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

import static reactor.core.publisher.Mono.error;
@Slf4j
@Service
@AllArgsConstructor
public class PeopleService {
    private PeopleRepository peopleRepository;
    private CepService cepService;

    public Flux<PersonEntity> findAll() {
        return peopleRepository.findAll()
                .doFinally(message -> log.info("Showing all peolple!"));
    }

    public Mono<PersonEntity> findById(String id) {
        return peopleRepository.findById(id)
                .switchIfEmpty(error(new ApiNotFoundException()))
                .doOnSuccess(message -> log.info("ID '{}' found!", id))
                .doOnError(error -> log.error("ID '{}' not found!", id));
    }

    public Mono<PersonEntity> create(PersonEntity personEntity) {
        return cepService.searchCepAndSavaToDataBase(personEntity)
                .flatMap(peopleRepository::save)
                .doOnSuccess(message -> log.info("Person '{}' created with success", personEntity.getNome()));
    }

    public Mono<Void> deleteById(String id) {
        return peopleRepository.deleteById(id)
                .doFinally(message -> log.info("Deleting by ID '{}'!", id));
    }

    public Mono<PersonEntity> update(String id, PersonEntity personEntity) {
        return peopleRepository.findById(id)
                .map((PersonEntity person) -> {
                    personEntity.setId(id);
                    peopleRepository.save(person);
                    return personEntity; })
                .flatMap(cepService::searchCepAndSavaToDataBase)
                .flatMap(peopleRepository::save)
                .switchIfEmpty(error(new ApiNotFoundException()))
                .doOnSuccess(message -> log.info("Updating a person ID '{}' with success!", id))
                .doOnError(error -> log.error("ID '{}' not found!", id));
    }

    public Mono<Void> deleteAll() {
        return peopleRepository.deleteAll()
                .doFinally(message -> log.info("Deleting all people with success!"));
    }

}
