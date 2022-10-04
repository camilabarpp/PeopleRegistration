package sprint5.peoplepegistration.patterns.strategy;

import org.springframework.stereotype.Service;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.service.PeopleService;

@Service
public class PayByCreditCard implements PayStrategy {
    private boolean signedIn;
    private boolean signedIn2;
    private boolean signedIn3;
    private final ShoppingCartService shoppingCartService;

    private final PeopleService peopleService;

    public PayByCreditCard(ShoppingCartService shoppingCartService, PeopleService peopleService) {
        this.shoppingCartService = shoppingCartService;
        this.peopleService = peopleService;
    }

    @Override
    public String pay(String paymentAmount) {
        if (signedIn && signedIn2 && signedIn3) {
            if (paymentAmount.startsWith("0,0")) {
                return  "Total amount R$ 0,00" +
                        "\nShopping cart is empty!";
            } else {
                shoppingCartService.deleteShoppingCart();
                return "Data verification has been sucessfull. \n" +"Paying " + paymentAmount + " using CreditCard.";
            }
        } else {
            return "Wrong number card, date expiration or cvv!";
        }
    }
    public void verify(String id, CreditCard.builder creditCard) {
        var found = peopleService.findById(id);
        found.map(x -> x.getCreditCard().getNumber());
        var numberAccount = false;

 //               found.getCreditCard().getNumber());
        found.map(x -> x.getCreditCard().getDateExpiration());
        var dateExpirationAccount = false;

        found.map(x -> x.getCreditCard().getCvv());
        var cvvAccount = false;

/*        var cvvAccount = creditCard.getCvv().equals(
                found.getCreditCard().getCvv());*/
        setSignedIn(numberAccount, dateExpirationAccount, cvvAccount);
    }

    public void setSignedIn(boolean signedIn, boolean signedIn2, boolean signedIn3) {
        this.signedIn = signedIn;
        this.signedIn2 = signedIn2;
        this.signedIn3 = signedIn3;
    }
}