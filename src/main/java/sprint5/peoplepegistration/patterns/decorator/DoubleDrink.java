package sprint5.peoplepegistration.patterns.decorator;

import sprint5.peoplepegistration.cafe.model.drink.Drink;

public class DoubleDrink extends DrinkDecorator {

    //Decorator de DoubleDrink
   public DoubleDrink(Drink drink) { //Referecia ao Drink original
       super(drink);
   }
    @Override
    public String serve() {
        drink.serve();
        drink.serve();
        return "\nAdding double of " + drink;
    }

    @Override
    public Double getPrice() {
        return drink.getPrice() * 2.00;
    }
}
