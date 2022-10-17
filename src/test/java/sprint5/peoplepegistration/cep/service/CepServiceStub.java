package sprint5.peoplepegistration.cep.service;

import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.model.request.PersonRequest;

public class CepServiceStub {
    public static CepEntity createAEntityCep() {
        return CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    public static PersonEntity createAEntity() {
        return new PersonEntity.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .cepEntity(createAEntityCep())
                .build();
    }

    public static PersonRequest createARequest() {
        return new PersonRequest.builder()
                .nome("Debora")
                .dataDeNascimento("02/07/1983")
                .cepEntity(createAEntityCep())
                .build();
    }
}
