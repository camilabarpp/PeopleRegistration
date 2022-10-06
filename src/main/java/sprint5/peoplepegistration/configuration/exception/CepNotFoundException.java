package sprint5.peoplepegistration.configuration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CepNotFoundException extends RuntimeException {

    public CepNotFoundException(String s) {
        super(s);
    }
}
