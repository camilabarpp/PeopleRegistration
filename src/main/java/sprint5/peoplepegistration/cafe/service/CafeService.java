package sprint5.peoplepegistration.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.drink.Drink;
import sprint5.peoplepegistration.cafe.model.drink.Expresso;
import sprint5.peoplepegistration.cafe.model.drink.Tea;
import sprint5.peoplepegistration.patterns.decorator.DoubleDrink;
import sprint5.peoplepegistration.patterns.decorator.Milk;

@Service
@AllArgsConstructor
public class CafeService {
    private final ShoppingCartService shoppingCartService;

    public Mono<String> order(String name, Drink drink) {
        var sum = shoppingCartService.getPrice(drink.getPrice());
        shoppingCartService.addtolist(name);
        return Mono.just("Ordering a " + name +
                drink.serve() + "\nPrice: R$ " +
                drink.getPrice() + "\n" + "\nTotal amount: R$ " + sum);
    }
    public Mono<String> expresso() {
        return order("Expresso", new Expresso());
    }
    public Mono<String> tea() {
        return order("Tea ", new Tea());
    }

    public Mono<String> lungo() {
        return order("Lungo ", new DoubleDrink(new Expresso()));
    }

    public Mono<String> cafeAuLait() {
        return order("Cafe Au Lait ", new Milk(new Expresso()));
    }

    public Mono<String> englishTea() {
        return order("English Tea  ", new Milk(new Tea()));
    }

    public Mono<String> britishTea() {
        return order("British Tea  ",
                new DoubleDrink(
                        new Tea()));
    }

    public Mono<String> menu() {
        return Mono.just("""
                1 - Expresso      R$ 1,50
                2 - Tea           R$ 1,00
                3 - Lungo         R$ 3,00
                4 - Cafe Au Lait  R$ 2,00
                5 - English Tea   R$ 1,50
                6 - British Tea   R$ 2,00""");

    }
}
