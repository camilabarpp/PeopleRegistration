package sprint5.peoplepegistration.people.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import sprint5.peoplepegistration.cafe.model.payment.CreditCard;
import sprint5.peoplepegistration.cafe.model.payment.DebitCard;
import sprint5.peoplepegistration.cafe.model.payment.PayPal;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
    private CepEntity cepEntity;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private DebitCard debitCard;
    private CreditCard creditCard;
    private PayPal paypal;

    public PersonRequest(builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataDeNascimento = builder.dataDeNascimento;
        this.cepEntity = builder.cepEntity;
        this.creditCard = builder.creditCard;
        this.debitCard = builder.debitCard;
        this.paypal = builder.paypal;
    }

    @Getter
    @NoArgsConstructor
    public static class builder {
        @Id
        private String id;
        private String nome;
        private String dataDeNascimento;
        private CepEntity cepEntity;
        private DebitCard debitCard;
        private CreditCard creditCard;
        private PayPal paypal;

        public builder id(String id) {
            this.id = id;
            return this;
        }

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