package sprint5.peoplepegistration.configuration.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sprint5.peoplepegistration.configuration.exception.errorresponse.ErrorResponse;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApiExceptionHandler extends DefaultResponseErrorHandler {

    @ExceptionHandler(ShoppingCartException.class)
    @ResponseStatus (NOT_FOUND)
    public ErrorResponse shoppingCartException(ShoppingCartException e) {
        return  ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Shopping cart is empty!")
                .field(NOT_FOUND.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(CepNotFoundException.class)
    @ResponseStatus (NOT_FOUND)
    public ErrorResponse cepNotFoundException(CepNotFoundException e) {
        return  ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Sorry, CEP not found!")
                .field(NOT_FOUND.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus (NOT_FOUND)
    public ErrorResponse apiNotFoundException(ApiNotFoundException e) {
        return  ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Sorry, data not found!")
                .field(NOT_FOUND.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse nullPointerException(NullPointerException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Sorry, null data!")
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus (BAD_REQUEST)
    public ErrorResponse handleException(ResponseStatusException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse httpClientErrorException(HttpClientErrorException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus (INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }
}
