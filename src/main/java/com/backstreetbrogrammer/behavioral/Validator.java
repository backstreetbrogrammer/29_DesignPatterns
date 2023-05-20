package com.backstreetbrogrammer.behavioral;

public class Validator {
    private final ValidationStrategy strategy;

    public Validator(final ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean isValid(final String s) {
        return strategy.isValid(s);
    }
}
