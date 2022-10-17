package sprint5.peoplepegistration.people.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.service.PeopleService;

import java.util.List;

@Component
@AllArgsConstructor
public class PeopleServiceFacade {
    private final PeopleService peopleService;

    public Flux<PersonEntity> findAll() {
        return peopleService.findAll();
    }

    public Mono<PersonEntity> findById(String id) {
        return peopleService.findById(id);
    }

    public Flux<PersonEntity> findByName(String nome) {
        return peopleService.findByName(nome);
    }

    public Mono<PersonEntity> create(PersonEntity personEntity) {
        return peopleService.create(personEntity);
    }

    public Mono<PersonEntity> update(String id, PersonEntity personEntity) {
        return peopleService.update(id, personEntity);
    }

    public Mono<Void> deleteAllByIds(List<String> id) {
        return peopleService.deletePeolpleByIDs(id);
    }
}
