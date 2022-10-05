package sprint5.peoplepegistration.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.service.PaymentService;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;
import sprint5.peoplepegistration.people.service.PeopleService;

import static reactor.core.publisher.Mono.just;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PeopleRepository peopleRepository;

    @GetMapping("/credito/")
    public Mono<String> verifyAndPayCreditCard(@RequestBody PersonEntity creditCard) {
        return paymentService.payAndVerifyCreditCard(creditCard);
    }

    @GetMapping("/credito2/") //Método teste para verificar os dados de pagamento no banco
    public Mono<Boolean> verifyAndPayCreditCard2(@RequestBody PersonEntity creditCard) {
        return peopleRepository.existsByIdAndCreditCard_NumberAndCreditCard_DateExpirationAndCreditCard_Cvv(
                creditCard.getId(),
                creditCard.getCreditCard().getNumber(),
                creditCard.getCreditCard().getDateExpiration(),
                creditCard.getCreditCard().getCvv());
    }
}
