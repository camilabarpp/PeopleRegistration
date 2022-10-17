package sprint5.peoplepegistration.cafe.controller.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.service.facade.CafeServiceFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static sprint5.peoplepegistration.cafe.controller.stubs.CafeServiceStub.menu;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CafeControllerFacade.class, CafeServiceFacade.class})
class CafeControllerFacadeTest {

    @InjectMocks
    CafeControllerFacade cafeControllerFacade;

    @Mock
    CafeServiceFacade cafeServiceFacade;

    @Test
    @DisplayName("Should delete the shopping cart")
    void shouldDeleteShoppingCart() {
        when(cafeServiceFacade.deleteShoppingCart())
                .thenReturn(Mono.just("Total amount: R$ "));

        cafeControllerFacade.deleteShoppingCart();

        verify(this.cafeServiceFacade, atLeastOnce()).deleteShoppingCart();
    }

    @Test
    @DisplayName("Should show the menu")
    void shouldShowMenu() {
        var expected = menu;

        when(cafeServiceFacade.showMenu()).thenReturn(Mono.just(menu));

       var actual = cafeControllerFacade.menu().block();

       assertEquals(expected, actual);
       verify(cafeServiceFacade, atLeastOnce()).showMenu();
    }

    @Test
    @DisplayName("Should buy an expresso")
    void shouldBuyAnExpresso() {
        var expected = "Expresso";

        when(cafeServiceFacade.expresso()).thenReturn(Mono.just("Expresso"));

        var actual = cafeControllerFacade.expresso().block();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a tea")
    void shouldBuyATea() {
        var expected = "Tea";

        when(cafeServiceFacade.tea()).thenReturn(Mono.just("Tea"));

        var actual = cafeControllerFacade.tea().block();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a lungo")
    void shouldBuyALungo() {
        var expected = "Lungo";

        when(cafeServiceFacade.lungo()).thenReturn(Mono.just("Lungo"));

        var actual = cafeControllerFacade.lungo().block();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a cafe au lait")
    void shouldBuyACafeAuLait() {
        var expected = "Cafe Au Lait";

        when(cafeServiceFacade.cafeAuLait()).thenReturn(Mono.just("Cafe Au Lait"));

        var actual = cafeControllerFacade.cafeAuLait().block();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy an English Tea")
    void shouldBuyAnEnglishTea() {
        var expected = "English Tea";

        when(cafeServiceFacade.englishTea()).thenReturn(Mono.just("English Tea"));

        var actual = cafeControllerFacade.englishTea().block();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a British Tea")
    void shouldBuyABritishTea() {
        var expected = "British Tea ";

        when(cafeServiceFacade.britishTea()).thenReturn(Mono.just("British Tea "));

        var actual = cafeControllerFacade.britishTea().block();

        assertEquals(expected, actual);
    }
}