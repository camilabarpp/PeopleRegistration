package sprint5.peoplepegistration.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.controller.facade.ShoppingCartControllerFacade;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/shoppingcart")
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartControllerFacade shoppingCartControllerFacade;

    @GetMapping
    public Flux<String> showShoppingCart() {
        return shoppingCartControllerFacade.showShoppingCart();
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteShoppingCart() {
        return shoppingCartControllerFacade.deleteShoppingCart();
    }
}
