package sprint5.peoplepegistration.cafe.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cafe.controller.facade.ShoppingCartControllerFacade;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/shoppingcart")
@AllArgsConstructor
@Api(value = "People Registration API")
public class ShoppingCartController {
    private final ShoppingCartControllerFacade shoppingCartControllerFacade;

    @GetMapping
    @ApiOperation("Show the shopping cart")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Show the shopping cart"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Flux<String> showShoppingCart() {
        return shoppingCartControllerFacade.showShoppingCart();
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete the shopping cart")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Delete the shopping cart"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Mono<Void> deleteShoppingCart() {
        return shoppingCartControllerFacade.deleteShoppingCart();
    }
}
