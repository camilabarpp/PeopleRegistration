package sprint5.peoplepegistration.cafe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.controller.facade.ShoppingCartControllerFacade;
import sprint5.peoplepegistration.configuration.exception.ShoppingCartException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ShoppingCartControllerFacade.class, ShoppingCartController.class})
class ShoppingCartControllerTest {

    @InjectMocks
    ShoppingCartController shoppingCartController;

    @Mock
    ShoppingCartControllerFacade shoppingCartControllerFacade;

    @Mock
    List<String> shoppingCartList;


    @Test
    @DisplayName("Should show the shpping cart with success")
    void shouldShowShoppingCartWithSuccess() {
        var expected = Flux.fromIterable(shoppingCartList);

        when(shoppingCartControllerFacade.showShoppingCart())
                .thenReturn(expected);

        var actual = shoppingCartController
                .showShoppingCart();

        assertEquals(expected, actual);
        verify(shoppingCartControllerFacade, atLeastOnce()).showShoppingCart();
    }

    @Test
    @DisplayName("Should throws ShoppingCartException when shopping cart is empty")
    void shouldThrowsShoppingCartExceptionWhenShoppingCartIsEmpty() {
        doThrow(new ShoppingCartException("0,00" +
                "\nShopping cart is empty!"))
                .when(shoppingCartControllerFacade).showShoppingCart();

        assertThrows(ShoppingCartException.class, () -> shoppingCartController.showShoppingCart());
    }

    @Test
    @DisplayName("Should delete the shopping cart with success")
    void shouldDeleteShoppingCartWithSucess() {
        var expected = Mono.empty();

        when(shoppingCartControllerFacade.deleteShoppingCart())
                .thenReturn(expected.then());

        shoppingCartController
                .deleteShoppingCart();

        verify(shoppingCartControllerFacade, atLeastOnce()).deleteShoppingCart();
    }
}