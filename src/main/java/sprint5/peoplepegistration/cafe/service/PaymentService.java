package sprint5.peoplepegistration.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.patterns.strategy.PayByCreditCard;
import sprint5.peoplepegistration.patterns.strategy.PayByDebitCard;
import sprint5.peoplepegistration.patterns.strategy.PayByPayPal;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Service
@AllArgsConstructor
public class PaymentService {
    private PayByCreditCard payByCreditCard;
    private PayByDebitCard payByDebitCard;
    private PayByPayPal payByPayPal;
    public Flux<String> payAndVerifyCreditCard(PersonEntity personEntity) {
        return payByCreditCard.showShoppingCart(personEntity);
    }

    public Flux<String> payAndVerifyDebitCard(PersonEntity personEntity) {
        return payByDebitCard.showShoppingCart(personEntity);
    }

    public Flux<String> payAndVerifyPayPal(PersonEntity personEntity) {
        payByPayPal.verify(personEntity);
        return payByPayPal.showShoppingCart(personEntity);
    }
}
