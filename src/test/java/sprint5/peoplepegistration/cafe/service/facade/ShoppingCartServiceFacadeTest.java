package sprint5.peoplepegistration.cafe.service.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.model.drink.Drink;
import sprint5.peoplepegistration.cafe.model.drink.Expresso;
import sprint5.peoplepegistration.cafe.service.ShoppingCartService;
import sprint5.peoplepegistration.configuration.exception.ShoppingCartException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ShoppingCartServiceFacade.class, ShoppingCartService.class})
class ShoppingCartServiceFacadeTest {

    @InjectMocks
    ShoppingCartServiceFacade shoppingCartServiceFacade;

    @Mock
    ShoppingCartService shoppingCartService;

    @Mock
    List<String> shoppingCartList;

    @Mock
    List<String> names;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should get the product name")
    void shouldGetProductNames() {
        var expected = Flux.fromIterable(names);

        when(shoppingCartService.getNames())
                .thenReturn(expected);

        var actual = shoppingCartServiceFacade
                .getNames();

        assertEquals(expected, actual);
        verify(shoppingCartService, atLeastOnce()).getNames();
    }

    @Test
    @DisplayName("Should get the product price")
    void shouldGetProductPrice() {
        Drink drink = new Expresso();
        var expected = drink.getPrice();

        when(shoppingCartService.getPrice(drink.getPrice())).thenReturn(expected);

        var actual = shoppingCartServiceFacade.getPrice(drink.getPrice());

        verify(shoppingCartService, atLeastOnce()).getPrice(expected);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should add the product name and price into list")
    void shouldAddToListProductsNameAndPriceIntoList() {
        String name = "Expresso";

        doNothing().
                when(shoppingCartService).addtolist(name);

        shoppingCartServiceFacade.addToList(name);

        verify(shoppingCartService, atLeastOnce()).addtolist(name);
    }

    @Test
    @DisplayName("Should show the shpping cart with success")
    void shouldShowShoppingCartWithSuccess() {
        var expected = Flux.fromIterable(shoppingCartList);

        when(shoppingCartService.showShoppingCart())
                .thenReturn(expected);

        var actual = shoppingCartServiceFacade
                .showShoppingCart();

        assertEquals(expected, actual);
        verify(shoppingCartService, atLeastOnce()).showShoppingCart();
    }

    @Test
    @DisplayName("Should throws ShoppingCartException when shopping cart is empty")
    void shouldThrowsShoppingCartExceptionWhenShoppingCartIsEmpty() {
        doThrow(new ShoppingCartException("0,00" +
                "\nShopping cart is empty!"))
                .when(shoppingCartService).showShoppingCart();

        assertThrows(ShoppingCartException.class, () -> shoppingCartServiceFacade.showShoppingCart());
    }

    @Test
    @DisplayName("Should delete the shopping cart with success")
    void shouldDeleteShoppingCartWithSucess() {
        var expected = Mono.empty();

        when(shoppingCartService.deleteShoppingCart())
                .thenReturn(expected.then());

        shoppingCartServiceFacade.deleteShoppingCart();

        verify(shoppingCartService, atLeastOnce()).deleteShoppingCart();
    }
}