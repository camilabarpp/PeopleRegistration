package sprint5.peoplepegistration.cafe.service.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.cafe.service.PaymentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static reactor.test.StepVerifier.create;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityInvalid;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityValid;
import static sprint5.peoplepegistration.cafe.service.stubs.PaymentServiceStub.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PaymentServiceFacade.class, PaymentService.class})
class PaymentServiceFacadeTest {

    @InjectMocks
    PaymentServiceFacade paymentServiceFacade;

    @Mock
    PaymentService paymentService;

    @Test
    @DisplayName("Should verify credit card data and pay amounts with success")
    void shouldVerifyAndPayWithCreditCard() {
        var expected = creditCard;

        when(paymentService.payAndVerifyCreditCard(personEntityValid))
                .thenReturn(Flux.just(expected));

        create(paymentServiceFacade.payAndVerifyCreditCard(personEntityValid))
                .assertNext(exp -> assertEquals(expected, exp))
                .verifyComplete();

        var actual = paymentServiceFacade.payAndVerifyCreditCard(personEntityValid).blockFirst();
        verify(paymentService, atLeastOnce()).payAndVerifyCreditCard(personEntityValid);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify credit card data and fail when credit card data is invalid")
    void shouldNotPayAmountWhenCreditCardDataIsInvalid() {
        var expected = creditCardNotValid;

        when(paymentService.payAndVerifyCreditCard(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        create(paymentServiceFacade.payAndVerifyCreditCard(personEntityInvalid))
                .expectNext(expected)
                .verifyComplete();

        var actual = paymentServiceFacade.payAndVerifyCreditCard(personEntityInvalid).blockFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify debit card data and pay amounts with success")
    void payAndVerifyDebitCard() {
        var expected = debitCard;

        when(paymentService.payAndVerifyDebitCard(personEntityValid))
                .thenReturn(Flux.just(expected));

        create(paymentServiceFacade.payAndVerifyDebitCard(personEntityValid))
                .assertNext(exp -> assertEquals(expected, exp))
                .verifyComplete();

        var actual = paymentServiceFacade.payAndVerifyDebitCard(personEntityValid).blockFirst();
        verify(paymentService, atLeastOnce()).payAndVerifyDebitCard(personEntityValid);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify debit card data and fail when debit card data is invalid")
    void shouldNotPayAmountWhenDebitCardDataIsInvalid() {
        var expected = debitCardNotValid;

        when(paymentService.payAndVerifyDebitCard(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        create(paymentServiceFacade.payAndVerifyDebitCard(personEntityInvalid))
                .expectNext(expected)
                .verifyComplete();

        var actual = paymentServiceFacade.payAndVerifyDebitCard(personEntityInvalid).blockFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify PayPal data and pay amounts with success")
    void payAndVerifyPayPal() {
        var expected = paypal;

        when(paymentService.payAndVerifyPayPal(personEntityValid))
                .thenReturn(Flux.just(expected));

        create(paymentServiceFacade.payAndVerifyPayPal(personEntityValid))
                .assertNext(exp -> assertEquals(expected, exp))
                .verifyComplete();

        var actual = paymentServiceFacade.payAndVerifyPayPal(personEntityValid).blockFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify Paypal data and fail when Paypal data is invalid")
    void shouldNotPayAmountWhenPaypalDataIsInvalid() {
        var expected = paypalNotValid;

        when(paymentService.payAndVerifyPayPal(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        create(paymentServiceFacade.payAndVerifyPayPal(personEntityInvalid))
                .expectNext(expected)
                .verifyComplete();

        var actual = paymentServiceFacade.payAndVerifyPayPal(personEntityInvalid).blockFirst();
        assertEquals(expected, actual);
    }

}