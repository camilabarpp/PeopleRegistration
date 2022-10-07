package sprint5.peoplepegistration.patterns.decorator;

import sprint5.peoplepegistration.cafe.model.drink.Drink;

public abstract class DrinkDecorator implements Drink {

    //Apartir dessa composição, nós vamos conseguir adcionar novas funcionalidades
    //e ainda respeitar a ‘interface’ Drink
    protected final Drink drink;

    protected DrinkDecorator(Drink drink) {
        this.drink = drink;
    }
}
