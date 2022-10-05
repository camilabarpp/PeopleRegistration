package sprint5.peoplepegistration.people.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private String id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataDeNascimento;
    private CepEntity cepEntity;

    public PersonResponse(builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataDeNascimento = builder.dataDeNascimento;
        this.cepEntity = builder.cepEntity;
    }

    @Getter
    @NoArgsConstructor
    public static class builder {
        @Id
        private String id;
        private String nome;
        private String dataDeNascimento;
        private CepEntity cepEntity;

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

        public PersonResponse build() {
            return new PersonResponse(this);
        }
    }
}
