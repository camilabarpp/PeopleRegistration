package sprint5.peoplepegistration.patterns.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.repository.PeopleRepository;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {PayByCreditCard.class, PeopleRepository.class, ShoppingCartService.class})
class PayByCreditCardTest {

    @InjectMocks
    PayByCreditCard payByCreditCard;

    @Mock
    PeopleRepository peopleRepository;

    @Mock
    ShoppingCartService shoppingCartService;

    @Mock
    WebTestClient client;

    CreditCard creditCard = CreditCard.builder()
            .id("1")
            .number("123456")
            .dateExpiration("01/01")
            .cvv("123")
            .build();

    CepEntity cepEntity = CepEntity.builder()
            .cep("9402070")
            .build();
    PersonEntity personEntity1 = new PersonEntity.builder()
            .id("1")
            .nome("Camila")
            .dataDeNascimento("02/07/1996")
            .cepEntity(cepEntity)
            .creditCard(creditCard)
            .build();


    @Test
    void showShoppingCart() {
    }

    @Test
    void pay() {
    }

    @Test
    void verify() {
    }
}