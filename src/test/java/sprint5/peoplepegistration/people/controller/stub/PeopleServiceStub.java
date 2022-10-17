package sprint5.peoplepegistration.people.controller.stub;

import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;

public class PeopleServiceStub {
    public static CepEntity createAEntityCep() {
        return CepEntity.builder()
                .cep("94020070")
                .logradouro("Rua Joao Dutra")
                .bairro("Salgado Filho")
                .localidade("Gravataí")
                .uf("RS")
                .build();
    }

    public static CreditCard createACreditCard() {
        return CreditCard.builder()
                .number("123456")
                .dateExpiration("01/01")
                .cvv("123")
                .build();
    }

    public static PersonEntity createAEntity() {
        return new PersonEntity.builder()
                .id("1")
                .nome("Camila")
                .dataDeNascimento("02/07/1996")
                .cepEntity(createAEntityCep())
                .creditCard(createACreditCard())
                .build();
    }
}
