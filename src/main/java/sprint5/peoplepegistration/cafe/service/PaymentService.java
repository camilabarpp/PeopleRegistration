package sprint5.peoplepegistration.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.model.payment.DebitCard;
import sprint5.peoplepegistration.cafe.model.payment.PayPal;
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
    public Mono<String> payAndVerifyCreditCard(PersonEntity creditCard) {
        payByCreditCard.verify(creditCard);
        return payByCreditCard.pay(String.valueOf(shoppingCartService.showShoppingCart()));
    }
}
