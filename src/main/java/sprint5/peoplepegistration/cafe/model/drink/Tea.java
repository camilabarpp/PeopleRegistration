package sprint5.peoplepegistration.cafe.model.drink;

import lombok.ToString;

@ToString
public class Tea implements Drink {
    @Override
    public String serve() {
        return "\nAdding 100ML of tea...";
    }

    @Override
    public Double getPrice() {
        return 1.0;
    }
}
