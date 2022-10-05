package sprint5.peoplepegistration.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static reactor.core.publisher.Mono.just;

@RestController
@RequestMapping("/api/v1/shoppingcart")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public Flux<String> showShoppingCart() {
        var text = just("Total amount: R$ ");
        return text.concatWith(shoppingCartService.showShoppingCart());
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteShoppingCart() {
        return shoppingCartService.deleteShoppingCart();
    }
}
