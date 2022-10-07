package sprint5.peoplepegistration.patterns.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

import static reactor.core.publisher.Mono.just;

@Service
@AllArgsConstructor
public class PayByPayPal implements PayStrategy {
    private final ShoppingCartService shoppingCartService;
    private final PeopleRepository peopleRepository;

    public Flux<String> showShoppingCart(PersonEntity personEntity) {
        var text = just("Total amount: R$ ");
        return text.concatWith(shoppingCartService.showShoppingCart())
                .concatWith(pay(personEntity));
    }
    @Override
    public Mono<String> pay(PersonEntity personEntity) {
        return verify(personEntity).map(verifyIfDataIsCorrect -> {
            if (Boolean.TRUE.equals(verifyIfDataIsCorrect)) {
                shoppingCartService.deleteShoppingCart();
                return """
                        
                        Data verification has been sucessfull.\s
                        Paying using PayPal.""";
            } else {
                return "\nWrong email or password!";
            }
        });
    }

    @Override
    public Mono<Boolean> verify(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndPaypalEmailAndPaypalPassword(
                personEntity.getId(),
                personEntity.getPaypal().getEmail(),
                personEntity.getPaypal().getPassword()
        );
    }
}
