package com.backstreetbrogrammer.creational;

public class ProductFactory {

    public static Product createProduct(final String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }
}
