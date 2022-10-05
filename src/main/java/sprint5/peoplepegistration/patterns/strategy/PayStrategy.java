package sprint5.peoplepegistration.patterns.strategy;

import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

public interface PayStrategy {
    Mono<String> pay(Mono<String> paymentAmount, PersonEntity personEntity);
    Mono<Boolean> verify(PersonEntity personEntity);
}
