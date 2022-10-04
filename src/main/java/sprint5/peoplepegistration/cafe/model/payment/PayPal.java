package sprint5.peoplepegistration.cafe.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayPal {
    private String email;
    private String password;

    public PayPal(builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }

    @Getter
    @NoArgsConstructor
    public static class builder {
        private String email;
        private String password;

        public builder email(String email) {
            this.email = email;
            return this;
        }

        public builder password(String password) {
            this.password = password;
            return this;
        }

        public PayPal build() {
            return new PayPal(this);
        }
    }
}
