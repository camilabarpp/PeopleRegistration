package sprint5.peoplepegistration.people.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<PersonEntity, String> {
    Mono<String> getByNome(String nome);
    Mono<Boolean> existsByNome(String nome);
    Mono<Boolean> existsByIdAndNome(String id, String nome);
    Mono<Boolean> existsByDataDeNascimento(String dataDeNascimento);
    Mono<Boolean> existsByIdAndCreditCard_NumberAndCreditCard_DateExpirationAndCreditCard_Cvv(
            String id,
            String numberAccount,
            String dateExpiration,
            String cvv);

    Mono<Boolean> existsByIdAndDebitCard_NumberAndDebitCard_DateExpirationAndDebitCard_Cvv(
            String id,
            String numberAccount,
            String dateExpiration,
            String cvv);

    Mono<Boolean> existsByIdAndPaypal_EmailAndPaypal_Password(
            String id,
            String email,
            String password);
}
