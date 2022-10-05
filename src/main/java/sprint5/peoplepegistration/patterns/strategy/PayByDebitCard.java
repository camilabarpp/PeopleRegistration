package sprint5.peoplepegistration.patterns.strategy;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.service.PeopleService;

@Service
public class PayByDebitCard implements PayStrategy {
    private boolean signedIn;
    private boolean signedIn2;
    private boolean signedIn3;
    private final ShoppingCartService shoppingCartService;
    private final PeopleService cadastroServices;

    public PayByDebitCard(ShoppingCartService shoppingCartService, PeopleService cadastroServices) {
        this.shoppingCartService = shoppingCartService;
        this.cadastroServices = cadastroServices;
    }

    @Override
    public Mono<String> pay(String paymentAmount) {
        if (signedIn && signedIn2 && signedIn3) {
            if (paymentAmount.startsWith("0,0")) {
                return Mono.just("""
                        
                        Total amount R$ 0,00
                        Shopping cart is empty!""");
            } else {
                shoppingCartService.deleteShoppingCart();
                return Mono.just("\nData verification has been sucessfull. \n" +"Paying " + paymentAmount + " using DebitCard.");
            }
        } else {
            return Mono.just("\nWrong number card, date expiration or cvv!");
        }
    }

/*    public void verify(String id, DebitCard.builder debitCard) {
        var found = cadastroServices.findById(id);
        var numberAccount = debitCard.getNumber().equals(
                found.getDebitCard().getNumber());
        var dateExpirationAccount = debitCard.getDateExpiration().equals(
                found.getDebitCard().getDateExpiration());
        var cvvAccount = debitCard.getCvv().equals(
                found.getDebitCard().getCvv());
        setSignedIn(numberAccount, dateExpirationAccount, cvvAccount);
    }*/

    public void setSignedIn(boolean signedIn, boolean signedIn2, boolean signedIn3) {
        this.signedIn = signedIn;
        this.signedIn2 = signedIn2;
        this.signedIn3 = signedIn3;
    }
}
