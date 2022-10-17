package sprint5.peoplepegistration.cafe.model.payment;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CreditCard {
    private String id;
    private String number;
    private String dateExpiration;
    private String cvv;
}
