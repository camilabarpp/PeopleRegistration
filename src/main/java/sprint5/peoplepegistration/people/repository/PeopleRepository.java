package sprint5.peoplepegistration.people.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<PersonEntity, String> {
    Mono<Boolean> existsByIdAndCreditCardNumberAndCreditCardDateExpirationAndCreditCardCvv(
            String id,
            String numberAccount,
            String dateExpiration,
            String cvv);

    Mono<Boolean> existsByIdAndDebitCardNumberAndDebitCardDateExpirationAndDebitCardCvv(
            String id,
            String numberAccount,
            String dateExpiration,
            String cvv);

    Mono<Boolean> existsByIdAndPaypalEmailAndPaypalPassword(
            String id,
            String email,
            String password);
    @Query("{ 'nome': { $regex: ?0, $options:  'i' } }")
    Flux<PersonEntity> findByNome(String nome);
}
