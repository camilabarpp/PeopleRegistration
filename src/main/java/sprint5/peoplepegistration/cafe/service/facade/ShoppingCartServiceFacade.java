package sprint5.peoplepegistration.cafe.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;

@Component
@AllArgsConstructor
public class ShoppingCartServiceFacade {
    private ShoppingCartService shoppingCartService;

    public Flux<String> getNames() {
        return shoppingCartService.getNomes();
    }



    public Mono<Void> deleteShoppingCart() {
        return shoppingCartService.deleteShoppingCart();
    }


}
