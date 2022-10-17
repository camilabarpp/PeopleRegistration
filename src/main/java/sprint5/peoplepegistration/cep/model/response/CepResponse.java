package sprint5.peoplepegistration.cep.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CepResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
