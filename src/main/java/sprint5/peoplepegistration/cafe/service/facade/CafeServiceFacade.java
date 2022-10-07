package sprint5.peoplepegistration.cafe.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.drink.Drink;
import sprint5.peoplepegistration.cafe.service.CafeService;

@Component
@AllArgsConstructor
public class CafeServiceFacade {
    private final CafeService cafeService;

    public Mono<String> deleteShoppingCart() {
        return cafeService.deleteShoppingCart();
    }
    public Mono<String> showMenu() {
        return cafeService.menu();
    }
    public Mono<String> order(String name, Drink drink) {
        return cafeService.order(name, drink);
    }

    public Mono<String> expresso() {
        return cafeService.expresso();
    }

    public Mono<String> tea() {
        return cafeService.tea();
    }

    public Mono<String> lungo() {
        return cafeService.lungo();
    }

    public Mono<String> cafeAuLait() {
        return cafeService.cafeAuLait();
    }

    public Mono<String> englishTea() {
        return cafeService.englishTea();
    }

    public Mono<String> britishTea() {
        return cafeService.britishTea();
    }

}
