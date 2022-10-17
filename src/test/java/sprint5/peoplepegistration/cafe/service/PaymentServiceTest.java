package sprint5.peoplepegistration.cafe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.patterns.strategy.PayByCreditCard;
import sprint5.peoplepegistration.patterns.strategy.PayByDebitCard;
import sprint5.peoplepegistration.patterns.strategy.PayByPayPal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static reactor.test.StepVerifier.create;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityInvalid;
import static sprint5.peoplepegistration.cafe.controller.stubs.PaymentControllerStub.personEntityValid;
import static sprint5.peoplepegistration.cafe.service.stubs.PaymentServiceStub.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {PaymentService.class})
class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    PayByCreditCard payByCreditCard;

    @Mock
    PayByDebitCard payByDebitCard;

    @Mock
    PayByPayPal payByPayPal;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should verify credit card data and pay amounts with success")
    void shouldVerifyAndPayWithCreditCard() {
        var expected = creditCard;

        when(payByCreditCard.showShoppingCart(personEntityValid))
                .thenReturn(Flux.just(expected));

        create(paymentService.payAndVerifyCreditCard(personEntityValid))
                .assertNext(exp -> assertEquals(expected, exp))
                .verifyComplete();

        var actual = paymentService.payAndVerifyCreditCard(personEntityValid).blockFirst();
        verify(payByCreditCard, atLeastOnce()).showShoppingCart(personEntityValid);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify credit card data and fail when credit card data is invalid")
    void shouldNotPayAmountWhenCreditCardDataIsInvalid() {
        var expected = creditCardNotValid;

        when(payByCreditCard.showShoppingCart(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        create(paymentService.payAndVerifyCreditCard(personEntityInvalid))
                .expectNext(expected)
                .verifyComplete();

        var actual = paymentService.payAndVerifyCreditCard(personEntityInvalid).blockFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify debit card data and pay amounts with success")
    void payAndVerifyDebitCard() {
        var expected = debitCard;

        when(payByDebitCard.showShoppingCart(personEntityValid))
                .thenReturn(Flux.just(expected));

        create(paymentService.payAndVerifyDebitCard(personEntityValid))
                .assertNext(exp -> assertEquals(expected, exp))
                .verifyComplete();

        var actual = paymentService.payAndVerifyDebitCard(personEntityValid).blockFirst();
        verify(payByDebitCard, atLeastOnce()).showShoppingCart(personEntityValid);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify debit card data and fail when debit card data is invalid")
    void shouldNotPayAmountWhenDebitCardDataIsInvalid() {
        var expected = debitCardNotValid;

        when(payByDebitCard.showShoppingCart(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        create(paymentService.payAndVerifyDebitCard(personEntityInvalid))
                .expectNext(expected)
                .verifyComplete();

        var actual = paymentService.payAndVerifyDebitCard(personEntityInvalid).blockFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify PayPal data and pay amounts with success")
    void payAndVerifyPayPal() {
        var expected = paypal;

        when(payByPayPal.showShoppingCart(personEntityValid))
                .thenReturn(Flux.just(expected));

        create(paymentService.payAndVerifyPayPal(personEntityValid))
                .assertNext(exp -> assertEquals(expected, exp))
                .verifyComplete();

        var actual = paymentService.payAndVerifyPayPal(personEntityValid).blockFirst();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should verify Paypal data and fail when Paypal data is invalid")
    void shouldNotPayAmountWhenPaypalDataIsInvalid() {
        var expected = paypalNotValid;

        when(payByPayPal.showShoppingCart(personEntityInvalid))
                .thenReturn(Flux.just(expected));

        create(paymentService.payAndVerifyPayPal(personEntityInvalid))
                .expectNext(expected)
                .verifyComplete();

        var actual = paymentService.payAndVerifyPayPal(personEntityInvalid).blockFirst();
        assertEquals(expected, actual);
    }


}