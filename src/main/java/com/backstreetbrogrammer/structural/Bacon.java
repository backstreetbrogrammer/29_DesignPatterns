package com.backstreetbrogrammer.structural;

public class Bacon extends PizzaDecorator {

    public Bacon(final Pizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return String.format("%s%s", super.decorate(), decorateWithBacon());
    }

    private String decorateWithBacon() {
        return " with super tasty bacon";
    }

}
