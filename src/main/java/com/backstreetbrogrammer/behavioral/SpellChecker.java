package com.backstreetbrogrammer.behavioral;

public class SpellChecker extends ProcessingObject<String> {
    @Override
    protected String handleWork(final String input) {
        return input.replaceAll("Udemy", "Guidemy");
    }
}
