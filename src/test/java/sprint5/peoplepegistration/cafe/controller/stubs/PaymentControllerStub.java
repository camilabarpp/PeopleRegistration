package sprint5.peoplepegistration.cafe.controller.stubs;

import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.model.payment.DebitCard;
import sprint5.peoplepegistration.cafe.model.payment.PayPal;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

public class PaymentControllerStub {

    public static final CreditCard creditCard = CreditCard.builder()
            .number("123456")
            .dateExpiration("01/01")
            .cvv("123")
            .build();

    public static final DebitCard debitCard = DebitCard.builder()
            .number("123456")
            .dateExpiration("01/01")
            .cvv("123")
            .build();

    public static final PayPal payPal = PayPal.builder()
            .email("camila@gmail.com")
            .password("123456")
            .build();

   public static final PersonEntity personEntityValid = new PersonEntity.builder()
           .creditCard(creditCard)
           .debitCard(debitCard)
           .paypal(payPal)
           .build();

   public static final PersonEntity personEntityInvalid = new PersonEntity.builder()
            .build();
}
