package sprint5.peoplepegistration.cafe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.controller.facade.CafeControllerFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CafeController.class, CafeControllerFacade.class})
class CafeControllerTest {

    @InjectMocks
    CafeController cafeController;

    @Mock
    CafeControllerFacade cafeControllerFacade;

    @Test
    @DisplayName("Should delete the shopping cart")
    void shouldDeleteShoppingCart() {
        var expected = "Total amount: R$ ";

        when(cafeControllerFacade.deleteShoppingCart())
                .thenReturn(Mono.just(expected));

        var actual = cafeController.deleteShoppingCart().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).deleteShoppingCart();
    }

    @Test
    @DisplayName("Should show the menu")
    void shouldShowMenu() {
        var expected = menu;

        when(cafeControllerFacade.menu()).thenReturn(Mono.just(menu));

       var actual = cafeController.menu().block();

       assertEquals(expected, actual);
       verify(cafeControllerFacade, atLeastOnce()).menu();
    }

    @Test
    @DisplayName("Should buy an expresso")
    void shouldBuyAnExpresso() {
        var expected = "Expresso";

        when(cafeControllerFacade.expresso()).thenReturn(Mono.just(expected));

        var actual = cafeController.expresso().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).expresso();
    }

    @Test
    @DisplayName("Should buy a tea")
    void shouldBuyATea() {
        var expected = "Tea";

        when(cafeControllerFacade.tea()).thenReturn(Mono.just(expected));

        var actual = cafeController.tea().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).tea();
    }

    @Test
    @DisplayName("Should buy a lungo")
    void shouldBuyALungo() {
        var expected = "Lungo";

        when(cafeControllerFacade.lungo()).thenReturn(Mono.just(expected));

        var actual = cafeController.lungo().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).lungo();
    }

    @Test
    @DisplayName("Should buy a cafe au lait")
    void shouldBuyACafeAuLait() {
        var expected = "Cafe Au Lait";

        when(cafeControllerFacade.cafeAuLait()).thenReturn(Mono.just(expected));

        var actual = cafeController.cafeAuLait().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).cafeAuLait();
    }

    @Test
    @DisplayName("Should buy an English Tea")
    void shouldBuyAnEnglishTea() {
        var expected = "English Tea";

        when(cafeControllerFacade.englishTea()).thenReturn(Mono.just(expected));

        var actual = cafeController.englishTea().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).englishTea();
    }

    @Test
    @DisplayName("Should buy a British Tea")
    void shouldBuyABritishTea() {
        var expected = "British Tea ";

        when(cafeControllerFacade.britishTea()).thenReturn(Mono.just(expected));

        var actual = cafeController.britishTea().block();

        assertEquals(expected, actual);
        verify(cafeControllerFacade, atLeastOnce()).britishTea();
    }

    String menu = """
                1 - Expresso      R$ 1,50
                2 - Tea           R$ 1,00
                3 - Lungo         R$ 3,00
                4 - Cafe Au Lait  R$ 2,00
                5 - English Tea   R$ 1,50
                6 - British Tea   R$ 2,00""";
}