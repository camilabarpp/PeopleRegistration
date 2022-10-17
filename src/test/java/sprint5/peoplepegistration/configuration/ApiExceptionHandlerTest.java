package sprint5.peoplepegistration.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sprint5.peoplepegistration.configuration.exception.ApiExceptionHandler;
import sprint5.peoplepegistration.configuration.exception.ApiNotFoundException;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;
import sprint5.peoplepegistration.configuration.exception.ShoppingCartException;
import sprint5.peoplepegistration.configuration.exception.errorresponse.ErrorResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ApiExceptionHandlerTest.class, ErrorResponse.class})
class ApiExceptionHandlerTest {
    @InjectMocks
    private ApiExceptionHandler exceptionHandler;
    @Mock
    private ErrorResponse errorObject;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve lançar ShoppingCartException")
    void shoppingCartException() {
        ErrorResponse response = exceptionHandler
                .shoppingCartException(new ShoppingCartException(
                        errorObject.getParameter()));

        assertNotNull(response);
        assertEquals("ShoppingCartException", response.getParameter());
        assertEquals("NOT_FOUND", response.getField());
    }

    @Test
    @DisplayName("Deve lançar ApiNotFoundException")
    void apiNotFoundException() {
        ErrorResponse response = exceptionHandler
                .apiNotFoundException(new ApiNotFoundException());

        assertNotNull(response);
        assertEquals("ApiNotFoundException", response.getParameter());
        assertEquals("NOT_FOUND", response.getField());
        assertEquals("Sorry, data not found!", response.getMessage());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Deve lançar CepNotFoundException")
    void cepNotFoundException() {
        ErrorResponse response = exceptionHandler
                .cepNotFoundException(new CepNotFoundException(
                        errorObject.getMessage()));

        assertNotNull(response);
        assertEquals("CepNotFoundException", response.getParameter());
        assertEquals("NOT_FOUND", response.getField());
        assertEquals("Sorry, CEP not found!", response.getMessage());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Deve lançar NullPointerException")
    void nullPointerException() {
        ErrorResponse response = exceptionHandler
                .nullPointerException(new NullPointerException(
                        errorObject.getParameter()));

        assertNotNull(response);
        assertEquals("Sorry, null data!", response.getMessage());
        assertNotNull(response.getTimestamp());
        assertEquals("NullPointerException", response.getParameter());
        assertEquals("INTERNAL_SERVER_ERROR", response.getField());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException")
    void shouldThowsResponseStatusException() {
        ErrorResponse response = exceptionHandler
                .handleException(new ResponseStatusException(
                        HttpStatus.BAD_REQUEST));

        assertNotNull(response);
        assertEquals("ResponseStatusException", response.getParameter());
        assertEquals("BAD_REQUEST", response.getField());
    }

    @Test
    @DisplayName("Deve lançar HttpClientErrorException")
    void shouldThowsHttpClientErrorException() {
        ErrorResponse response = exceptionHandler
                .httpClientErrorException(new HttpClientErrorException(
                        HttpStatus.BAD_REQUEST
                ));

        assertNotNull(response);
        assertEquals("HttpClientErrorException", response.getParameter());
        assertEquals("BAD_REQUEST", response.getField());
    }

    @Test
    @DisplayName("Deve lançar Exception")
    void shouldThowsException() {
        ErrorResponse response = exceptionHandler
                .handleException(new Exception(
                        errorObject.getParameter()));
        assertNotNull(response);
        assertEquals("Exception", response.getParameter());
        assertEquals("INTERNAL_SERVER_ERROR", response.getField());
    }
}
