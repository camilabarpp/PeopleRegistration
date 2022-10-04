package sprint5.peoplepegistration.configuration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sprint5.peoplepegistration.configuration.exception.errorresponse.ErrorResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public class CepNotFoundException extends RuntimeException {
    private final ErrorResponse personResponse;

    public CepNotFoundException(ErrorResponse personResponse) {
        super();
        this.personResponse = personResponse;
    }
}
