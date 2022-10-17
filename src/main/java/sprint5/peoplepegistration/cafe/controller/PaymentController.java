package sprint5.peoplepegistration.cafe.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sprint5.peoplepegistration.cafe.controller.facade.PaymentControllerFacade;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
@Api("Payment Controller API")
public class PaymentController {
    private final PaymentControllerFacade paymentControllerFacade;

    @GetMapping("/credit/")
    @ApiOperation("Paying amounts with Credit Card")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Paying amounts with Credit Card"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Flux<String> verifyAndPayCreditCard(@RequestBody PersonEntity personEntity) {
        return paymentControllerFacade.verifyAndPayCreditCard(personEntity);
    }
    @GetMapping("/debit/")
    @ApiOperation("Paying amounts with Debit Card")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Paying amounts with Debit Card"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Flux<String> verifyAndPayDebitCard(@RequestBody PersonEntity personEntity) {
        return paymentControllerFacade.verifyAndPayDebitCard(personEntity);
    }
    @GetMapping("/paypal/")
    @ApiOperation("Paying amounts with PayPal")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Paying amounts with PayPal"),
            @ApiResponse(code = 404, message = "Schema not found"),
            @ApiResponse(code = 400, message = "Missing or invalid request body"),
            @ApiResponse(code = 500, message = "Internal error")})
    public Flux<String> verifyAndPayPayPal(@RequestBody PersonEntity personEntity) {
        return paymentControllerFacade.verifyAndPayPayPal(personEntity);
    }
}
