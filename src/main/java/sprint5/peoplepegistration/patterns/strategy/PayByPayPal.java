package sprint5.peoplepegistration.patterns.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;
import sprint5.peoplepegistration.people.service.PeopleService;

@Service
@AllArgsConstructor
public class PayByPayPal implements PayStrategy {
    private final ShoppingCartService shoppingCartService;
    private final PeopleRepository peopleRepository;

    @Override
    public Mono<String> pay(Mono<String> paymentAmount, PersonEntity personEntity) {
        return verify(personEntity).map(teste -> {
            if (Boolean.TRUE.equals(teste)) {
                shoppingCartService.deleteShoppingCart();
                return "Data verification has been sucessfull. \n" +"Paying " +
                        paymentAmount + " using PayPal.";
            } else {
                return "Wrong email or password!";
            }
        });
    }

    @Override
    public Mono<Boolean> verify(PersonEntity personEntity) {
        return peopleRepository.existsByIdAndPaypal_EmailAndPaypal_Password(
                personEntity.getId(),
                personEntity.getPaypal().getEmail(),
                personEntity.getPaypal().getPassword()
        );
    }
}
