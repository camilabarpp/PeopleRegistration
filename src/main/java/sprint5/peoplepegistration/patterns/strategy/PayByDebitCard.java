package sprint5.peoplepegistration.patterns.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

@Service
@AllArgsConstructor
public class PayByDebitCard implements PayStrategy {
    private final ShoppingCartService shoppingCartService;
    private final PeopleRepository peopleRepository;

    @Override
    public Mono<String> pay(Mono<String> paymentAmount, PersonEntity personEntity) {
        return verify(personEntity).map(teste -> {
            if (Boolean.TRUE.equals(teste)) {
                shoppingCartService.deleteShoppingCart();
                return "Data verification has been sucessfull. \n" +"Paying " + paymentAmount + " using DebitCard.";
            } else {
                return "Wrong number card, date expiration or cvv!";
            }
        });
    }

    @Override
    public Mono<Boolean> verify(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndDebitCard_NumberAndDebitCard_DateExpirationAndDebitCard_Cvv(
                personEntity.getId(),
                personEntity.getDebitCard().getNumber(),
                personEntity.getDebitCard().getDateExpiration(),
                personEntity.getDebitCard().getCvv()
        );
    }
}
