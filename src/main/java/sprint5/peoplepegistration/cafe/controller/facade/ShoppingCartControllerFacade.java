package sprint5.peoplepegistration.cafe.controller.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.facade.ShoppingCartServiceFacade;

@Component
@AllArgsConstructor
public class ShoppingCartControllerFacade {
    private final ShoppingCartServiceFacade shoppingCartServiceFacade;

    public Flux<String> showShoppingCart() {
        return shoppingCartServiceFacade.showShoppingCart();
    }
    public Mono<Void> deleteShoppingCart() {
        return shoppingCartServiceFacade.deleteShoppingCart();
    }
}
