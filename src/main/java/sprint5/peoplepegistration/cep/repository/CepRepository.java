package sprint5.peoplepegistration.cep.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;

@Repository
public interface CepRepository extends ReactiveMongoRepository<CepEntity, String> {
}
