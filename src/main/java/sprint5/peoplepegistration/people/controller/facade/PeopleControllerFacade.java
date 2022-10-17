package sprint5.peoplepegistration.people.controller.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.service.facade.PeopleServiceFacade;

import java.util.List;
@Component
@AllArgsConstructor
public class PeopleControllerFacade {
    private final PeopleServiceFacade peopleServiceFacade;

    public Flux<PersonEntity> findAll() {
        return peopleServiceFacade.findAll();
    }

    public Mono<PersonEntity> findByID(String id) {
        return peopleServiceFacade.findById(id);
    }

    public Flux<PersonEntity> findByName(String nome) {
        return peopleServiceFacade.findByName(nome);
    }

    public Mono<PersonEntity> create(PersonEntity personEntity) {
        return peopleServiceFacade.create(personEntity);
    }

    public Mono<PersonEntity> update(String id, PersonEntity personEntity) {
        return peopleServiceFacade.update(id, personEntity);
    }

    public Mono<Void> deleteAllByIDs(List<String> id) {
        return peopleServiceFacade.deleteAllByIds(id);
    }

}
