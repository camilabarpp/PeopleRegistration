package sprint5.peoplepegistration.cep.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class CepEntity {
    @Id
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "CEP of the user")
    private String cep;
    @ApiModelProperty(notes = "Street of the user")
    private String logradouro;
    @ApiModelProperty(notes = "Neighborhood of the user")
    private String bairro;
    @ApiModelProperty(notes = "City of the user")
    private String localidade;
    @ApiModelProperty(notes = "State of the user")
    private String uf;
}

