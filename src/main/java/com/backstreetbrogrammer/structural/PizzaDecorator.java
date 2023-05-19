package com.backstreetbrogrammer.structural;

public abstract class PizzaDecorator implements Pizza {
    private Pizza pizza;

    public PizzaDecorator(final Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String decorate() {
        return pizza.decorate();
    }
}
