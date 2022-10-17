package sprint5.peoplepegistration.configuration.webClient.cep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.configuration.exception.CepNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static reactor.test.StepVerifier.create;
import static sprint5.peoplepegistration.configuration.webClient.cep.IntegrationCepStub.integrationCepResponse;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {IntegrationCepClient.class})
class IntegrationCepClientTest {
    @InjectMocks
    IntegrationCepClient integrationCepClient;

    private static ClientAndServer server;

    @BeforeAll
    static void startServer(){
        server = startClientAndServer();
    }

    @BeforeEach
    void setUp() {
        WebClient webClient = WebClient.builder()
                .baseUrl(String.format("http://localhost:%d", server.getPort()))
                .build();

        integrationCepClient = new IntegrationCepClient(webClient);
    }

    @AfterAll
    static void stopServer(){
        server.close();
    }

    @Test
    @DisplayName("Should find a CEP with success")
    void shouldFindACEPWithSuccess() throws Exception {
        CepEntity expected = integrationCepResponse();

        HttpRequest request = HttpRequest.request()
                .withPath("/ws/94020070/json")
                .withMethod("GET");

        ObjectMapper mapper = new ObjectMapper();

        var body = mapper.writeValueAsString(integrationCepResponse());

        HttpResponse response = HttpResponse.response(body)
                .withStatusCode(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        server.when(request).respond(response);

        create(integrationCepClient.findCep("94020070"))
                .assertNext((CepEntity entity) ->
                        assertEquals(expected, entity))
                .verifyComplete();
    }

    @Test
    @DisplayName("Should throws CepNotFoundException when do not search a valid CEP")
    void shouldThrowsApiNotFoundException() {
        IntegrationCepClient integration = mock(IntegrationCepClient.class);

        doThrow(CepNotFoundException.class)
                .when(integration).findCep("A");

        assertThrows(CepNotFoundException.class, () -> integration.findCep("A"));
    }
}