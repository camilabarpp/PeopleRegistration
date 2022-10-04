package sprint5.peoplepegistration.cafe.model.drink;

import lombok.ToString;

@ToString
public class Expresso implements Drink {
    @Override
    public String serve() {
        return "\nAdding 50ML of expresso...";
    }

    @Override
    public Double getPrice() {
        return 1.5;
    }
}
