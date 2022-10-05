package sprint5.peoplepegistration.patterns.strategy;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.service.PeopleService;

@Service
public class PayByPayPal {
    private boolean signedIn;
    private boolean signedIn2;
    private final ShoppingCartService shoppingCartService;
    private final PeopleService cadastroServices;

    public PayByPayPal(ShoppingCartService shoppingCartService, PeopleService cadastroServices) {
        this.shoppingCartService = shoppingCartService;
        this.cadastroServices = cadastroServices;
    }

/*    public void verify(String id, PayPal.builder payPal) {
        var found = cadastroServices.findById(id);
        var equals = payPal.getEmail().equals(
                found.getPaypal().getEmail());
        var equals2 = payPal.getPassword().equals(
                found.getPaypal().getPassword());
        setSignedIn(equals, equals2);
    }*/

/*    @Override
    public Mono<String> pay(String paymentAmount) {
        if (signedIn && signedIn2) {
            if (paymentAmount.startsWith("0")) {
                return Mono.just("""
                        
                        Total amount R$ 0,00
                        Shopping cart is empty!""");
            } else {
                shoppingCartService.deleteShoppingCart();
                return Mono.just("\nData verification has been sucessfull. \n" +"Paying " + paymentAmount + " using PayPal.");
            }
        } else {
            return Mono.just("\nWrong email or password!");
        }
    }*/

    public Mono<String> pay(String paymentAmount, PersonEntity personEntity) {
        return null;
    }

    public Mono<Boolean> verify(PersonEntity personEntity) {
        return null;
    }

    private void setSignedIn(boolean signedIn, boolean equals2) {
        this.signedIn = signedIn;
        this.signedIn2 = equals2;
    }
}
