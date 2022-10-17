package sprint5.peoplepegistration.cafe.service.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sprint5.peoplepegistration.cafe.service.CafeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;
import static sprint5.peoplepegistration.cafe.controller.stubs.CafeServiceStub.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CafeService.class, CafeServiceFacade.class})
class CafeServiceFacadeTest {
    @InjectMocks
    CafeServiceFacade cafeServiceFacade;

    @Mock
    CafeService cafeService;

    @Mock
    private ShoppingCartServiceFacade shoppingCartServiceFacade;

    @Test
    @DisplayName("Should delete the shopping cart")
    void shouldDeleteShoppingCart() {
        var expected = "Total amount: R$ ";

        when(cafeService.deleteShoppingCart())
                .thenReturn(Mono.just(expected));

        create(cafeServiceFacade.deleteShoppingCart())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.deleteShoppingCart().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should show the menu")
    void shouldShowTheMenu() {
        var expected = menu;

        when(cafeService.menu())
                .thenReturn(Mono.just(expected));

        create(cafeServiceFacade.showMenu())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.showMenu().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy an expresso")
    void shouldBuyAnExpresso() {
        var expected = expresso;

        when(cafeService.expresso())
                .thenReturn(Mono.just(expected));

        create(cafeServiceFacade.expresso())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.expresso().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a tea")
    void shouldBuyATea() {
        var expected = tea;

        when(cafeService.tea())
                .thenReturn(Mono.just(expected));

        create(cafeServiceFacade.tea())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.tea().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a lungo")
    void shouldBuyALungo() {
        var expected = lungo;

        when(cafeService.lungo())
                .thenReturn(Mono.just(expected));

        StepVerifier.create(cafeServiceFacade.lungo())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.lungo().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a café au lait")
    void shouldBuyACafeAuLait() {
        var expected = cafeAuLait;

        when(cafeService.cafeAuLait())
                .thenReturn(Mono.just(expected));

        StepVerifier.create(cafeServiceFacade.cafeAuLait())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.cafeAuLait().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy an English Tea")
    void shouldBuyAnEnglishTea() {
        var expected = englisTea;

        when(cafeService.englishTea())
                .thenReturn(Mono.just(expected));

        StepVerifier.create(cafeServiceFacade.englishTea())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.englishTea().block();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should buy a British Tea")
    void shouldBuyABritishTea() {
        var expected = britishTea;

        when(cafeService.britishTea())
                .thenReturn(Mono.just(expected));

        StepVerifier.create(cafeServiceFacade.britishTea())
                .expectNext(expected)
                .verifyComplete();

        var actual = cafeServiceFacade.britishTea().block();
        assertEquals(expected, actual);
    }

}