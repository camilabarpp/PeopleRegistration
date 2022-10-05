package sprint5.peoplepegistration.patterns.strategy;

import reactor.core.publisher.Mono;

public interface PayStrategy {
    Mono<String> pay(String paymentAmount);
    //todo refazer os métodos de pagamento usando a entidade pessoa diretamente
}
