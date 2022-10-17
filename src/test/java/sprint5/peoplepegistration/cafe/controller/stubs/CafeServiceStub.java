package sprint5.peoplepegistration.cafe.controller.stubs;

public class CafeServiceStub {

    public static String menu = """
                1 - Expresso      R$ 1,50
                2 - Tea           R$ 1,00
                3 - Lungo         R$ 3,00
                4 - Cafe Au Lait  R$ 2,00
                5 - English Tea   R$ 1,50
                6 - British Tea   R$ 2,00""";

    public static String expresso = """
            Ordering a Expresso
            Adding 50ML of expresso...
            Price: R$ 1.5

            Total amount: R$ 0.0""";

    public static String tea = """
            Ordering a Tea\s
            Adding 100ML of tea...
            Price: R$ 1.0

            Total amount: R$ 0.0""";

    public static String lungo = """
            Ordering a Lungo\s
            Adding double of Expresso()
            Price: R$ 3.0

            Total amount: R$ 0.0""";

    public static String cafeAuLait = """
            Ordering a Cafe Au Lait\s
            Adding 30ml of Milk and\s
            Adding 50ML of expresso...
            Price: R$ 2.0

            Total amount: R$ 0.0""";

    public static String englisTea = """
            Ordering a English Tea \s
            Adding 30ml of Milk and\s
            Adding 100ML of tea...
            Price: R$ 1.5

            Total amount: R$ 0.0""";

    public static String britishTea = """
            Ordering a British Tea \s
            Adding double of Tea()
            Price: R$ 2.0

            Total amount: R$ 0.0""";
}
