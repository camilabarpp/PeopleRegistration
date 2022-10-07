package sprint5.peoplepegistration.cafe.controller.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.cafe.service.facade.PaymentServiceFacade;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@Component
@AllArgsConstructor
public class PaymentControllerFacade {
    private final PaymentServiceFacade paymentServiceFacade;

    public Flux<String> verifyAndPayCreditCard(PersonEntity personEntity) {
        return paymentServiceFacade.payAndVerifyCreditCard(personEntity);
    }
    public Flux<String> verifyAndPayDebitCard(PersonEntity personEntity) {
        return paymentServiceFacade.payAndVerifyDebitCard(personEntity);
    }
    public Flux<String> verifyAndPayPayPal(PersonEntity personEntity) {
        return paymentServiceFacade.payAndVerifyPayPal(personEntity);
    }
}
