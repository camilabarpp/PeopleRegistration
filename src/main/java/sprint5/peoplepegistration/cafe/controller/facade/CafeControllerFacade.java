package sprint5.peoplepegistration.cafe.controller.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.facade.CafeServiceFacade;

@Component
@AllArgsConstructor
public class CafeControllerFacade {
    private final CafeServiceFacade cafeServiceFacade;

    public Mono<String> deleteShoppingCart() {
        return cafeServiceFacade.deleteShoppingCart();
    }
    public Mono<String> menu() {
        return cafeServiceFacade.showMenu();
    }
    public Mono<String> expresso() {
        return cafeServiceFacade.expresso();
    }
    public Mono<String> tea() {
        return cafeServiceFacade.tea();
    }
    public Mono<String> lungo() {
        return cafeServiceFacade.lungo();
    }
    public Mono<String> cafeAuLait() {
        return cafeServiceFacade.cafeAuLait();
    }
    public Mono<String> englishTea() {
        return cafeServiceFacade.englishTea();
    }
    public Mono<String> britishTea() {
        return cafeServiceFacade.britishTea();
    }
}
