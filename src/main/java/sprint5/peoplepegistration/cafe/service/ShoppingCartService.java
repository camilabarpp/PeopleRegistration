package sprint5.peoplepegistration.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.configuration.exception.ShoppingCartException;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    List<Double> items;
    List<String> nomes;

    public Flux<String> getNomes() {
        return Flux.fromIterable(nomes);
    }

    public Mono<Void> deleteShoppingCart() {
        if (!items.isEmpty()) {
            nomes.clear();
           items.clear();
        }
        return Mono.empty();
    }

    public Double getPrice(Double item) {
        items.add(item);
        return items.stream()
                .reduce(Double::sum).
                orElse(0d);
    }

    public void addtolist(String name) {
        nomes.add(0, name);
    }

    public Flux<String> showShoppingCart()
    {
        if (items.isEmpty()) {
            throw new ShoppingCartException("0,00" +
                    "\nShopping cart is empty!");
        } else {
            return Flux.just(String.valueOf(items.stream()
                    .reduce(Double::sum).
                    orElse(0d))).concatWith(getNomes());
        }
    }
}
