package sprint5.peoplepegistration.cafe.controller.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.facade.ShoppingCartServiceFacade;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Component
@AllArgsConstructor
public class ShoppingCartControllerFacade {

    private final ShoppingCartServiceFacade shoppingCartServiceFacade;

    public Mono<String> showShoppingCart() {
        return shoppingCartServiceFacade.showShoppingCart();
    }
    public Mono<Void> deleteShoppingCart() {
        return shoppingCartServiceFacade.deleteShoppingCart();
    }
}
