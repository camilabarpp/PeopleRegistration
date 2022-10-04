package sprint5.peoplepegistration.configuration.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sprint5.peoplepegistration.configuration.exception.errorresponse.ErrorResponse;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApiExceptionHandler extends DefaultResponseErrorHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus (BAD_REQUEST)
    public ErrorResponse webExchangeBindException(WebExchangeBindException e) {
        return  new ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(NOT_FOUND.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus (NOT_FOUND)
    public ErrorResponse apiNotFoundException(ApiNotFoundException e) {
        return  new ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Dados não encontrados!")
                .field(NOT_FOUND.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    //Erro para valores nulos
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse nullPointerException(NullPointerException e) {
        return new ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus (BAD_REQUEST)
    public ErrorResponse handleException(ResponseStatusException e) {
        return new ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse httpClientErrorException(HttpClientErrorException e) {
        return new ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus (INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }
}
