package sprint5.peoplepegistration.patterns.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

@Service
@AllArgsConstructor
public class PayByDebitCard implements PayStrategy {
    private final ShoppingCartService shoppingCartService;
    private final PeopleRepository peopleRepository;

    public Flux<String> showShoppingCart(PersonEntity personEntity) {
        return pay(personEntity)
                .concatWith(shoppingCartService.showShoppingCart());
    }

    @Override
    public Mono<String> pay(PersonEntity personEntity) {
        return verify(personEntity).map(teste -> {
            if (Boolean.TRUE.equals(teste)) {
                shoppingCartService.deleteShoppingCart();
                return """                        
                        Data verification has been sucessfull.\s
                        Paying using DebitCard.
                        \s""";
            } else {
                return "Wrong number card, date expiration or cvv!\n";
            }
        });
    }

    @Override
    public Mono<Boolean> verify(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndDebitCardNumberAndDebitCardDateExpirationAndDebitCardCvv(
                personEntity.getId(),
                personEntity.getDebitCard().getNumber(),
                personEntity.getDebitCard().getDateExpiration(),
                personEntity.getDebitCard().getCvv()
        );
    }
}
