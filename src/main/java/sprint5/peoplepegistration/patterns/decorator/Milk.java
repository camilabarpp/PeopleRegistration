package sprint5.peoplepegistration.patterns.decorator;

import sprint5.peoplepegistration.cafe.model.drink.Drink;

public class Milk extends DrinkDecorator {
    //Decorator de Milk
    public Milk(Drink drink) { //Referencia para o drink original
        super(drink);
    }
    @Override
    public String serve() {
        drink.serve();
        drink.serve();
        return "\nAdding 30ml of Milk and " + drink.serve();
    }

    @Override
    public Double getPrice() {
        return drink.getPrice() + 0.50;
    }

}
