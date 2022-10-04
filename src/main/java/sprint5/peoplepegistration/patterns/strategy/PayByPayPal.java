package sprint5.peoplepegistration.patterns.strategy;

import org.springframework.stereotype.Service;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.service.PeopleService;

@Service
public class PayByPayPal implements PayStrategy {
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

    @Override
    public String pay(String paymentAmount) {
        if (signedIn && signedIn2) {
            if (paymentAmount.startsWith("0")) {
                return """
                        
                        Total amount R$ 0,00
                        Shopping cart is empty!""";
            } else {
                shoppingCartService.deleteShoppingCart();
                return "\nData verification has been sucessfull. \n" +"Paying " + paymentAmount + " using PayPal.";
            }
        } else {
            return "\nWrong email or password!";
        }
    }

    private void setSignedIn(boolean signedIn, boolean equals2) {
        this.signedIn = signedIn;
        this.signedIn2 = equals2;
    }
}
