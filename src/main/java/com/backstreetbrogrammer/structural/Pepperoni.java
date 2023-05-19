package com.backstreetbrogrammer.structural;

public class Pepperoni extends PizzaDecorator {

    public Pepperoni(final Pizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return String.format("%s%s", super.decorate(), decorateWithPepperoni());
    }

    private String decorateWithPepperoni() {
        return " with fresh pepperoni";
    }

}
