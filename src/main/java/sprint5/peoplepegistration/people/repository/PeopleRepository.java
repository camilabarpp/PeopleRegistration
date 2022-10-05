package sprint5.peoplepegistration.people.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<PersonEntity, String> {
    Mono<Boolean> existsByCreditCard_Number(String numberCard);
    Mono<Boolean> existsByNome(String nome);
    Mono<Boolean> existsByIdAndNome(String id, String nome);
    Mono<Boolean> existsByDataDeNascimento(String dataDeNascimento);
    Mono<Boolean> existsByIdAndCreditCard_NumberAndCreditCard_DateExpirationAndCreditCard_Cvv(
            String id,
            String numberAccount,
            String dateExpiration,
            String cvv);
}
