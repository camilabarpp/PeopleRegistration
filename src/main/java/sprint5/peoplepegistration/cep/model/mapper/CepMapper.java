package sprint5.peoplepegistration.cep.model.mapper;

import lombok.experimental.UtilityClass;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.model.response.CepResponse;

@UtilityClass
public class CepMapper {

    public static CepResponse responseToEntity(CepEntity cepEntity) {
        return CepResponse.builder()
                .cep(cepEntity.getCep())
                .logradouro(cepEntity.getLogradouro())
                .bairro(cepEntity.getBairro())
                .localidade(cepEntity.getLocalidade())
                .uf(cepEntity.getUf())
                .build();
    }
}
