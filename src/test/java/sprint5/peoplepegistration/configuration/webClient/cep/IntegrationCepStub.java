package sprint5.peoplepegistration.configuration.webClient.cep;

import sprint5.peoplepegistration.cep.model.entity.CepEntity;

public class IntegrationCepStub {

    public static CepEntity integrationCepResponse(){
        return CepEntity.builder()
                .cep("94020-070")
                .logradouro("Rua João Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }
}
