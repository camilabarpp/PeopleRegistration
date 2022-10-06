package sprint5.peoplepegistration.cafe.service.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.CafeService;

@Component
@AllArgsConstructor
public class CafeServiceFacade {
    private final CafeService cafeService;

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
