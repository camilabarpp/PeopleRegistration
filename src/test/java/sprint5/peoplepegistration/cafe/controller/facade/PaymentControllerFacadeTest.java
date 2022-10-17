package sprint5.peoplepegistration.cafe.controller.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.cafe.service.facade.PaymentServiceFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityInvalid;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityValid;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PaymentControllerFacade.class, PaymentServiceFacade.class})
class PaymentControllerFacadeTest {

    @InjectMocks
    PaymentControllerFacade paymentControllerFacade;

    @Mock
    PaymentServiceFacade paymentServiceFacade;

    @Test
    @DisplayName("Should verify credit card data and pay amounts with success")
    void shouldVerifyAndPayWithCreditCard() {
        var expected = "Data verification has been sucessfull. \n" +
                "Paying using CreditCard.";

        when(paymentServiceFacade.payAndVerifyCreditCard(personEntityValid))
                .thenReturn(Flux.just("Data verification has been sucessfull. \n" +
                        "Paying using CreditCard."));

        var actual = paymentControllerFacade
                .verifyAndPayCreditCard(personEntityValid).blockFirst();

        assertEquals(expected, actual);
        verify(paymentServiceFacade, atLeastOnce()).payAndVerifyCreditCard(personEntityValid);
    }

    @Test
    @DisplayName("Should verify credit card data and not pay amounts when credit card is invalid")
    void souldVerifyAndNotPayWithCreditCardWhenCreditCardIsNotValid() {
        var expected = "Wrong number card, date expiration or cvv!";

        when(paymentServiceFacade.payAndVerifyCreditCard(personEntityInvalid))
                .thenReturn(Flux.just("Wrong number card, date expiration or cvv!"));

        var actual = paymentControllerFacade
                .verifyAndPayCreditCard(personEntityInvalid).blockFirst();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify debit card data and pay amounts with success")
    void shouldVerifyAndPayDebitCard() {
        var expected = "Data verification has been sucessfull. \n" +
                "Paying using DebitCard.";

        when(paymentServiceFacade.payAndVerifyDebitCard(personEntityValid))
                .thenReturn(Flux.just("Data verification has been sucessfull. \n" +
                        "Paying using DebitCard."));

        var actual = paymentControllerFacade
                .verifyAndPayDebitCard(personEntityValid).blockFirst();

        assertEquals(expected, actual);
        verify(paymentServiceFacade, atLeastOnce()).payAndVerifyDebitCard(personEntityValid);
    }

    @Test
    @DisplayName("Should verify debit card data and not pay amounts when debit card is invalid")
    void shouldVerifyAndNotPayWithDebitCardWhenDebitCardIsNotValid() {
        var expected = "Wrong number card, date expiration or cvv!";

        when(paymentServiceFacade.payAndVerifyDebitCard(personEntityInvalid))
                .thenReturn(Flux.just("Wrong number card, date expiration or cvv!"));

        var actual = paymentControllerFacade
                .verifyAndPayDebitCard(personEntityInvalid).blockFirst();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify paypal data and pay amounts with success")
    void shouldVerifyAndPayPayPal() {
        var expected = "Data verification has been sucessfull. \n" +
                "Paying using PayPal.";

        when(paymentServiceFacade.payAndVerifyPayPal(personEntityValid))
                .thenReturn(Flux.just("Data verification has been sucessfull. \n" +
                        "Paying using PayPal."));

        var actual = paymentControllerFacade
                .verifyAndPayPayPal(personEntityValid).blockFirst();

        assertEquals(expected, actual);
        verify(paymentServiceFacade, atLeastOnce()).payAndVerifyPayPal(personEntityValid);
    }

    @Test
    @DisplayName("Should verify paypal data and not pay amounts when PayPal is invalid")
    void shouldVerifyAndNotPayWithCPayPalWhenPayPalIsNotValid() {
        var expected = "Wrong email or password!";

        when(paymentServiceFacade.payAndVerifyPayPal(personEntityInvalid))
                .thenReturn(Flux.just("Wrong email or password!"));

        var actual = paymentControllerFacade
                .verifyAndPayPayPal(personEntityInvalid).blockFirst();

        assertEquals(expected, actual);
    }
}