package sprint5.peoplepegistration.configuration.webClient.cep;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;

@Component
@AllArgsConstructor
public class IntegrationCepClient {

    private WebClient webClient;

    public Mono<CepEntity> findCep(String cep) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ws/"+ cep + "/json")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, (ClientResponse::createException))
                .bodyToMono(CepEntity.class);

    }
}
