package sprint5.peoplepegistration.cafe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.drink.Drink;
import sprint5.peoplepegistration.cafe.model.drink.Expresso;
import sprint5.peoplepegistration.configuration.exception.ShoppingCartException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static reactor.test.StepVerifier.create;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = ShoppingCartService.class)
class ShoppingCartServiceTest {

    @InjectMocks
    ShoppingCartService shoppingCartService;

    @Mock
    List<Double> items;

    @Mock
    List<String> names;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should get the product name")
    void shouldGetProductNames() { //todo Continuar os testes com a classe ShoppingCart Service
        var actual = shoppingCartService.getNames();

        create(actual)
                .assertNext(exp -> equals(names.toString()))
                .verifyComplete();
    }


    @Test
    @DisplayName("Should delete the shopping cart")
    void shouldDeleteShoppingCart() {
        names.add(0, "Expresso");
        items.add(0, 1.0);

        var actual = shoppingCartService.deleteShoppingCart();

        create(actual.log())
                .expectNext()
                .verifyComplete();

        verify(names, atLeastOnce()).clear();
        verify(items, atLeastOnce()).clear();
    }

    @Test
    @DisplayName("Should get product price with success")
    void shouldGetProductPrice() {
        Drink drink = new Expresso();
        var actual = Mono.just(shoppingCartService.getPrice(drink.getPrice()));

        create(actual)
                .assertNext(exp -> equals(items))
                .verifyComplete();
    }

    @Test
    @DisplayName("Should add product to list with success")
    void shouldAddtolistWithSuccess() {
        shoppingCartService.addtolist("Expresso");

        verify(names, atLeastOnce()).add(0, "Expresso");
    }

    @Test
    @DisplayName("Should show the shopping cart when list is not empty")
    void shouldShowShoppingCartWithSuccess() {
        var expected = "\nTotal amount: R$ 0.0";

        var actual = shoppingCartService.showShoppingCart();

        create(actual)
                .expectNext(expected)
                .assertNext(exp -> equals(expected))
                .verifyComplete();
    }

    @Test
    @DisplayName("Should show the shopping cart when list is not empty")
    void shouldShowShoppingCart() {
        when(shoppingCartService.showShoppingCart()).thenThrow(ShoppingCartException.class);

        assertThrows(ShoppingCartException.class, () -> shoppingCartService.showShoppingCart(),
                "0,00 " + "\nShopping cart is empty!");
    }
}