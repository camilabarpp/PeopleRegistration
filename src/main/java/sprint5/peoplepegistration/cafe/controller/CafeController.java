package sprint5.peoplepegistration.cafe.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.controller.facade.CafeControllerFacade;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/menu")
@Api(value = "People Registration API")
public class CafeController {
    private final CafeControllerFacade cafeControllerFacade;
    @Autowired
    public CafeController(CafeControllerFacade cafeControllerFacade) {
        this.cafeControllerFacade = cafeControllerFacade;
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<String> deleteShoppingCart() {
        return cafeControllerFacade.deleteShoppingCart();
    }

    @GetMapping()
    public Mono<String> menu() {
        return cafeControllerFacade.menu();
    }

    @GetMapping("/1")
    public Mono<String> expresso() {
        return cafeControllerFacade.expresso();
    }

    @GetMapping("/2")
    public Mono<String> tea() {
        return cafeControllerFacade.tea();
    }

    @GetMapping("/3")
    public Mono<String> lungo() {
        return cafeControllerFacade.lungo();
    }

    @GetMapping("/4")
    public Mono<String> cafeAuLait() {
        return cafeControllerFacade.cafeAuLait();
    }

    @GetMapping("/5")
    public Mono<String> englishTea() {
          return cafeControllerFacade.englishTea();
    }

    @GetMapping("/6")
    public Mono<String> britishTea() {
        return cafeControllerFacade.britishTea();
    }
}
