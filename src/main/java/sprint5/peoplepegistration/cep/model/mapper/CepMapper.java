package sprint5.peoplepegistration.cep.model.mapper;

import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;
import sprint5.peoplepegistration.cep.model.entity.CepEntity;
import sprint5.peoplepegistration.cep.model.response.CepResponse;

@UtilityClass
public class CepMapper {

    public static CepEntity entityToResponse(CepResponse response) {
        return new CepEntity.builder()
                .cep(response.getCep())
                .logradouro(response.getLogradouro())
                .bairro(response.getBairro())
                .localidade(response.getLocalidade())
                .uf(response.getUf())
                .build();
    }

    public static CepResponse responseToEntity(CepEntity cepEntity) {
        return new CepResponse.builder()
                .cep(cepEntity.getCep())
                .logradouro(cepEntity.getLogradouro())
                .bairro(cepEntity.getBairro())
                .localidade(cepEntity.getLocalidade())
                .uf(cepEntity.getUf())
                .build();
    }
}
