package sprint5.peoplepegistration.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.CafeService;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/menu")
@AllArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<String> deleteShoppingCart() {
        return Mono.just("Total amount: R$ ");
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
    public Mono<String> lunog() {
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
