package com.backstreetbrogrammer.behavioral;

public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(final String input) {
        return "From Casper and Rishi: " + input;
    }
}
