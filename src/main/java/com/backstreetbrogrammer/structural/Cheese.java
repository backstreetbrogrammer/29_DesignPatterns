package com.backstreetbrogrammer.structural;

public class Cheese extends PizzaDecorator {

    public Cheese(final Pizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return String.format("%s%s", super.decorate(), decorateWithCheese());
    }

    private String decorateWithCheese() {
        return " with home made cheese";
    }

}
