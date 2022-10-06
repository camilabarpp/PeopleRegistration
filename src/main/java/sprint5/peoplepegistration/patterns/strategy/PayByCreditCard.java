package sprint5.peoplepegistration.patterns.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    public Flux<String> showShoppingCart(PersonEntity personEntity) {
        var text = just("Total amount: R$ ");
        return text.concatWith(shoppingCartService.showShoppingCart())
                .concatWith(pay(personEntity));
    }

    @Override
    public Mono<String> pay(PersonEntity personEntity) {
        return verify(personEntity).map(teste -> {
            if (Boolean.TRUE.equals(teste)) {
                shoppingCartService.deleteShoppingCart();
                return """
                        
                        Data verification has been sucessfull.\s
                        Paying using CreditCard.""";

            } else {
                return "\nWrong number card, date expiration or cvv!";
            }
        });
    }

    @Override
    public Mono<Boolean> verify(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndCreditCardNumberAndCreditCardDateExpirationAndCreditCardCvv(
                personEntity.getId(),
                personEntity.getCreditCard().getNumber(),
                personEntity.getCreditCard().getDateExpiration(),
                personEntity.getCreditCard().getCvv()
        );
    }
}