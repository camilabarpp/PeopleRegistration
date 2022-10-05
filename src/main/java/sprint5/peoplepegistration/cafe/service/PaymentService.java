package sprint5.peoplepegistration.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.patterns.strategy.PayByCreditCard;
import sprint5.peoplepegistration.patterns.strategy.PayByDebitCard;
import sprint5.peoplepegistration.patterns.strategy.PayByPayPal;
import sprint5.peoplepegistration.patterns.strategy.PayStrategy;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

import static reactor.core.publisher.Mono.just;

@Service
@AllArgsConstructor
public class PaymentService {
    private ShoppingCartService shoppingCartService;
    private PayByCreditCard payByCreditCard;
    private final PeopleRepository peopleRepository;

    private PayByDebitCard payByDebitCard;
    private PayByPayPal payByPayPal;

    public Mono<String> payAndVerifyCreditCard(PersonEntity personEntity) {
        payByCreditCard.verify(personEntity);
        return payByCreditCard.pay(shoppingCartService.showShoppingCart(), personEntity);
    }
}
