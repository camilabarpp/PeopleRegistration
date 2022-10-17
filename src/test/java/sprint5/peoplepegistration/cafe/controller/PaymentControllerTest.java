package sprint5.peoplepegistration.cafe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.cafe.controller.facade.PaymentControllerFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityInvalid;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityValid;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PaymentController.class, PaymentControllerFacade.class})
class PaymentControllerTest {
    @InjectMocks
    PaymentController paymentController;

    @Mock
    PaymentControllerFacade paymentControllerFacade;

    @Test
    @DisplayName("Should verify credit card data and pay amounts with success")
    void shouldVerifyAndPayWithCreditCard() {
        var expected = "Data verification has been sucessfull. \n" +
                "Paying using CreditCard.";

        when(paymentControllerFacade.verifyAndPayCreditCard(personEntityValid))
                .thenReturn(Flux.just(expected));

        var actual = paymentController
                .verifyAndPayCreditCard(personEntityValid).blockFirst();

        assertEquals(expected, actual);
        verify(paymentControllerFacade, atLeastOnce()).verifyAndPayCreditCard(personEntityValid);
    }

    @Test
    @DisplayName("Should verify credit card data and not pay amounts when credit card is invalid")
    void souldVerifyAndNotPayWithCreditCardWhenCreditCardIsNotValid() {
        var expected = "Wrong number card, date expiration or cvv!";

        when(paymentControllerFacade.verifyAndPayCreditCard(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        var actual = paymentController
                .verifyAndPayCreditCard(personEntityInvalid).blockFirst();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify debit card data and pay amounts with success")
    void shouldVerifyAndPayDebitCard() {
        var expected = "Data verification has been sucessfull. \n" +
                "Paying using DebitCard.";

        when(paymentControllerFacade.verifyAndPayDebitCard(personEntityValid))
                .thenReturn(Flux.just(expected));

        var actual = paymentController
                .verifyAndPayDebitCard(personEntityValid).blockFirst();

        assertEquals(expected, actual);
        verify(paymentControllerFacade, atLeastOnce()).verifyAndPayDebitCard(personEntityValid);
    }

    @Test
    @DisplayName("Should verify debit card data and not pay amounts when debit card is invalid")
    void shouldVerifyAndNotPayWithDebitCardWhenDebitCardIsNotValid() {
        var expected = "Wrong number card, date expiration or cvv!";

        when(paymentControllerFacade.verifyAndPayDebitCard(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        var actual = paymentController
                .verifyAndPayDebitCard(personEntityInvalid).blockFirst();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify paypal data and pay amounts with success")
    void shouldVerifyAndPayPayPal() {
        var expected = "Data verification has been sucessfull. \n" +
                "Paying using PayPal.";

        when(paymentControllerFacade.verifyAndPayPayPal(personEntityValid))
                .thenReturn(Flux.just(expected));

        var actual = paymentController
                .verifyAndPayPayPal(personEntityValid).blockFirst();

        assertEquals(expected, actual);
        verify(paymentControllerFacade, atLeastOnce()).verifyAndPayPayPal(personEntityValid);
    }

    @Test
    @DisplayName("Should verify paypal data and not pay amounts when PayPal is invalid")
    void shouldVerifyAndNotPayWithCPayPalWhenPayPalIsNotValid() {
        var expected = "Wrong email or password!";

        when(paymentControllerFacade.verifyAndPayPayPal(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        var actual = paymentController
                .verifyAndPayPayPal(personEntityInvalid).blockFirst();

        assertEquals(expected, actual);
    }
}