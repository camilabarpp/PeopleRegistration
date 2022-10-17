package sprint5.peoplepegistration.cafe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import sprint5.peoplepegistration.cafe.service.facade.ShoppingCartServiceFacade;

import static org.mockito.MockitoAnnotations.openMocks;
import static reactor.test.StepVerifier.create;
import static sprint5.peoplepegistration.cafe.controller.stubs.CafeServiceStub.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {CafeService.class})
class CafeServiceTest {

    @InjectMocks
    CafeService cafeService;

    @Mock
    private ShoppingCartServiceFacade shoppingCartServiceFacade;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should delete the shopping cart")
    void shouldDeleteShoppingCart() {
        var expected = "Total amount: R$ ";

        var actual = cafeService.deleteShoppingCart();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should show the menu")
    void shouldShowMenu() {
        var expected = menu;

        var actual = cafeService.menu();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should buy an expresso")
    void shouldBuyAnExpresso() {
        var expected = expresso;

        var actual = cafeService.expresso();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should buy a tea")
    void shouldBuyATea() {
        var expected = tea;

        var actual = cafeService.tea();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should buy a lungo")
    void shouldBuyALungo() {
        var expected = lungo;

        var actual = cafeService.lungo();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should buy a café au lait")
    void shouldBuyACafeAuLait() {
        var expected = cafeAuLait;

        var actual = cafeService.cafeAuLait();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should buy an English Tea")
    void shouldBuyAnEnglishTea() {
        var expected = englisTea;

        var actual = cafeService.englishTea();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should buy a British Tea")
    void shouldBuyABritishTea() {
        var expected = britishTea;

        var actual = cafeService.britishTea();

        create(actual.log())
                .expectNext(expected)
                .verifyComplete();
    }
}