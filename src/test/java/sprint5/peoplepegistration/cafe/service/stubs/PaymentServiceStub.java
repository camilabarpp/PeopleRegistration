package sprint5.peoplepegistration.cafe.service.stubs;

public class PaymentServiceStub {

    public static String creditCard = """               
            Data verification has been sucessfull.\s
            Paying using CreditCard.""";

    public static String creditCardNotValid = """               
            Wrong number card, date expiration or cvv!
            """;

    public static String debitCard = """               
            Data verification has been sucessfull.\s
            Paying using DebitCard.""";

    public static String debitCardNotValid = """               
            Wrong number card, date expiration or cvv!
            """;

    public static String paypal = """               
            Data verification has been sucessfull.\s
            Paying using PayPal.""";

    public static String paypalNotValid = """               
            Wrong email or password!\s
            """;
}
