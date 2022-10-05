package sprint5.peoplepegistration.patterns.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

import static reactor.core.publisher.Mono.just;

@Service
@RequiredArgsConstructor
public class PayByCreditCard implements PayStrategy {
    private boolean signedIn;
    private final ShoppingCartService shoppingCartService;
    private final PeopleRepository peopleRepository;

    @Override
    public Mono<String> pay(Mono<String> paymentAmount, PersonEntity personEntity) {
        return verify(personEntity).map(teste -> {
            if (Boolean.TRUE.equals(teste)) {
                shoppingCartService.deleteShoppingCart();
                return "Data verification has been sucessfull. \n" +"Paying " + paymentAmount + " using CreditCard.";
            } else {
                return "Wrong number card, date expiration or cvv!";
            }
        });
    }

    @Override
    public Mono<Boolean> verify(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndCreditCard_NumberAndCreditCard_DateExpirationAndCreditCard_Cvv(
                personEntity.getId(),
                personEntity.getCreditCard().getNumber(),
                personEntity.getCreditCard().getDateExpiration(),
                personEntity.getCreditCard().getCvv()
        );
    }
}