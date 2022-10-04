package sprint5.peoplepegistration.people.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<PersonEntity, String> {
}
