package sprint5.peoplepegistration.cafe.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.cafe.service.PaymentService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Component
@AllArgsConstructor
public class PaymentServiceFacade {

    private final PaymentService paymentService;

    public Flux<String> payAndVerifyCreditCard(PersonEntity creditCard) {
        return paymentService.payAndVerifyCreditCard(creditCard);
    }

    public Flux<String> payAndVerifyDebitCard(PersonEntity personEntity) {
        return paymentService.payAndVerifyDebitCard(personEntity);
    }

    public Flux<String> payAndVerifyPayPal(PersonEntity personEntity) {
        return paymentService.payAndVerifyPayPal(personEntity);
    }
}
