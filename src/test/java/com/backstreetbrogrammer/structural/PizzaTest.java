package com.backstreetbrogrammer.structural;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaTest {

    @Test
    void testPizzaDecorator() {
        final Pizza pizza1 = new Pepperoni(new PizzaImpl());
        assertEquals(pizza1.decorate(), "Yummy Pizza with fresh pepperoni");

        final Pizza pizza2 = new Bacon(new Cheese(new Pepperoni(new PizzaImpl())));
        assertEquals(pizza2.decorate(), "Yummy Pizza with fresh pepperoni with home made cheese with super tasty bacon");
    }
}
