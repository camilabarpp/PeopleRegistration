package sprint5.peoplepegistration.patterns.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.service.PeopleService;

import static reactor.core.publisher.Mono.just;

@Service
@RequiredArgsConstructor
public class PayByCreditCard implements PayStrategy {
    private Mono<Tuple2<PersonEntity, CreditCard>> signedIn;
    private boolean signedIn2;
    private final ShoppingCartService shoppingCartService;

    private final PeopleService peopleService;

    @Override
    public Mono<String> pay(String paymentAmount) {
        if (signedIn2) {
            if (paymentAmount.startsWith("0,0")) {
                return just("Total amount R$ 0,00" +
                        "\nShopping cart is empty!");
            } else {
                shoppingCartService.deleteShoppingCart();
                return just("Data verification has been sucessfull. \n"
                        +"Paying " + paymentAmount + " using CreditCard.");
            }
        } else {
            return just("Wrong number card, date expiration or cvv!");
        }
    }

    public void verify(PersonEntity creditCard) {
        var found = peopleService.findById(creditCard.getId());
        var aa = found.map(PersonEntity::getCreditCard);

        var teste = found.zipWith(aa)
                        .filter(objects -> objects.getT2().getNumber().equals(objects.getT1().getCreditCard().getNumber()));
        var tese= found.zipWith(aa)
                .flatMap(objects -> {
                    if(objects.getT2().getNumber().equals(objects.getT1().getCreditCard().getNumber())) {
                        return Mono.just(true);
                    } else {
                        return Mono.error(new ApiNotFoundException("Unable to add, since this parser does not belong to you"));
                    }
        });

                //setSignedIn(tese));

    }

    private void setSignedIn(Mono<Tuple2<PersonEntity, CreditCard>> tste) {
        this.signedIn = tste;
    }


/*    public boolean setSignedIn(Mono<PersonEntity> signedIn, Mono<PersonEntity> dateExpiration, Mono<>) {
        this.signedIn = signedIn;
        this.signedIn2 = signedIn2;
        this.signedIn3 = signedIn3;
    }*/
}