package sprint5.peoplepegistration.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.controller.facade.CafeControllerFacade;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/menu")
@AllArgsConstructor
public class CafeController {

    private final CafeControllerFacade cafeService;

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<String> deleteShoppingCart() {
        return cafeService.deleteShoppingCart();
    }

    @GetMapping()
    public Mono<String> menu() {
        return cafeService.menu();
    }

    @GetMapping("/1")
    public Mono<String> expresso() {
        return cafeService.expresso();
    }

    @GetMapping("/2")
    public Mono<String> tea() {
        return cafeService.tea();
    }

    @GetMapping("/3")
    public Mono<String> lungo() {
        return cafeService.lungo();
    }

    @GetMapping("/4")
    public Mono<String> cafeAuLait() {
        return cafeService.cafeAuLait();
    }

    @GetMapping("/5")
    public Mono<String> englishTea() {
          return cafeService.englishTea();
    }

    @GetMapping("/6")
    public Mono<String> britishTea() {
        return cafeService.britishTea();
    }
}
