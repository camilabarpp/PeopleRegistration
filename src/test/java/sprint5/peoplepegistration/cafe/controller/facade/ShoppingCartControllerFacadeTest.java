package sprint5.peoplepegistration.cafe.controller.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.facade.ShoppingCartServiceFacade;
import sprint5.peoplepegistration.configuration.exception.ShoppingCartException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ShoppingCartControllerFacade.class, ShoppingCartServiceFacade.class})
class ShoppingCartControllerFacadeTest {

    @InjectMocks
    ShoppingCartControllerFacade shoppingCartControllerFacade;

    @Mock
    ShoppingCartServiceFacade shoppingCartServiceFacade;

    @Mock
    List<String> shoppingCartList;

    @Test
    @DisplayName("Should show the shpping cart with success")
    void shouldShowShoppingCartWithSuccess() {
        var expected = Flux.fromIterable(shoppingCartList);

        when(shoppingCartServiceFacade.showShoppingCart())
                .thenReturn(expected);

        var actual = shoppingCartControllerFacade
                .showShoppingCart();

        assertEquals(expected, actual);
        verify(shoppingCartServiceFacade, atLeastOnce()).showShoppingCart();
    }

    @Test
    @DisplayName("Should throws ShoppingCartException when shopping cart is empty")
    void shouldThrowsShoppingCartExceptionWhenShoppingCartIsEmpty() {
        doThrow(new ShoppingCartException("0,00" +
                "\nShopping cart is empty!"))
                .when(shoppingCartServiceFacade).showShoppingCart();

        assertThrows(ShoppingCartException.class, () -> shoppingCartControllerFacade.showShoppingCart());
    }

    @Test
    @DisplayName("Should delete the shopping cart with success")
    void shouldDeleteShoppingCartWithSucess() {
        var expected = Mono.empty();

        when(shoppingCartServiceFacade.deleteShoppingCart())
                .thenReturn(expected.then());

        shoppingCartControllerFacade
                .deleteShoppingCart();

        verify(shoppingCartServiceFacade, atLeastOnce()).deleteShoppingCart();
    }
}