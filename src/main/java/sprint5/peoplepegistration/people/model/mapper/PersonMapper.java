package sprint5.peoplepegistration.people.model.mapper;

import lombok.experimental.UtilityClass;
import sprint5.peoplepegistration.people.model.entity.PersonEntity;
import sprint5.peoplepegistration.people.model.request.PersonRequest;
import sprint5.peoplepegistration.people.model.response.PersonResponse;

@UtilityClass
public class PersonMapper {

    public static PersonEntity requestPessoa(PersonRequest pessoaRequest) {
        return new PersonEntity.builder()
                .nome(pessoaRequest.getNome())
                .dataDeNascimento(pessoaRequest.getDataDeNascimento())
                .cepEntity(pessoaRequest.getCepEntity())
                .debitCard(pessoaRequest.getDebitCard())
                .creditCard(pessoaRequest.getCreditCard())
                .paypal(pessoaRequest.getPaypal())
                .build();
    }

    public static PersonResponse pessoaResponse(PersonEntity pessoa) {
        return new PersonResponse.builder()
                .id(pessoa.getId()) //Não tirar, senão não aparece o "id" no FindAll
                .nome(pessoa.getNome())
                .dataDeNascimento(pessoa.getDataDeNascimento())
                .cepEntity(pessoa.getCepEntity())
                .build();
    }
}
