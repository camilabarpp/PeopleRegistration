package sprint5.peoplepegistration.cafe.model.payment;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
public class PayPal {
    private String email;
    private String password;
}
