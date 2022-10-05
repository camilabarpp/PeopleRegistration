package sprint5.peoplepegistration.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.patterns.strategy.PayByCreditCard;
import sprint5.peoplepegistration.patterns.strategy.PayByDebitCard;
import sprint5.peoplepegistration.patterns.strategy.PayByPayPal;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Service
@AllArgsConstructor
public class PaymentService {
    private ShoppingCartService shoppingCartService;
    private PayByCreditCard payByCreditCard;
    private PayByDebitCard payByDebitCard;
    private PayByPayPal payByPayPal;

    public Mono<String> payAndVerifyCreditCard(PersonEntity personEntity) {
        payByCreditCard.verify(personEntity);
        return payByCreditCard.pay(shoppingCartService.showShoppingCart(), personEntity);
    }

    public Mono<String> payAndVerifyDebitCard(PersonEntity personEntity) {
        payByDebitCard.verify(personEntity);
        return payByDebitCard.pay(shoppingCartService.showShoppingCart(), personEntity);
    }

    public Mono<String> payAndVerifyPayPal(PersonEntity personEntity) {
        payByPayPal.verify(personEntity);
        return payByPayPal.pay(shoppingCartService.showShoppingCart(), personEntity);
    }
}
