package sprint5.peoplepegistration.people.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.model.payment.DebitCard;
import sprint5.peoplepegistration.cafe.model.payment.PayPal;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class PersonRequest {
    @NotNull
    @NotBlank(message = "Name can not be null or empty")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "BirthDate can not be null or empty")
    @NotNull
    private String dataDeNascimento;
    private CepEntity cepEntity;
    private DebitCard debitCard;
    private CreditCard creditCard;
    private PayPal paypal;

    public PersonRequest(builder builder) {
        this.nome = builder.nome;
        this.dataDeNascimento = builder.dataDeNascimento;
        this.cepEntity = builder.cepEntity;
        this.creditCard = builder.creditCard;
        this.debitCard = builder.debitCard;
        this.paypal = builder.paypal;
    }

    //@Getter
    @NoArgsConstructor
    public static class builder {
        private String nome;
        private String dataDeNascimento;
        private CepEntity cepEntity;
        private DebitCard debitCard;
        private CreditCard creditCard;
        private PayPal paypal;

        public builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public builder dataDeNascimento(String dataDeNascimento) {
            this.dataDeNascimento = dataDeNascimento;
            return this;
        }

        public builder cepEntity(CepEntity cepEntity) {
            this.cepEntity = cepEntity;
            return this;
        }

        public builder debitCard(DebitCard debitCard) {
            this.debitCard = debitCard;
            return this;
        }

        public builder creditCard(CreditCard creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public builder paypal(PayPal paypal) {
            this.paypal = paypal;
            return this;
        }

        public PersonRequest build() {
            return new PersonRequest(this);
        }
    }
}
