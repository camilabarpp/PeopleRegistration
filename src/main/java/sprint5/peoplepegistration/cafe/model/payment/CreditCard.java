package sprint5.peoplepegistration.cafe.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String number;
    private String dateExpiration;
    private String cvv;
    public CreditCard(builder builder) {
        this.number = builder.number;
        this.dateExpiration = builder.dateExpiration;
        this.cvv = builder.cvv;
    }
    @Getter
    @NoArgsConstructor
    public static class builder {
        private String number;
        private String dateExpiration;
        private String cvv;

        public builder number(String number) {
            this.number = number;
            return this;
        }

        public builder dateExpiration(String dateExpiration) {
            this.dateExpiration = dateExpiration;
            return this;
        }

        public builder cvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }
}
