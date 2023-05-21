package com.backstreetbrogrammer.behavioral;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessingObjectTest {

    @Test
    void testChainOfResponsibilityUsingLambdas() {
        final UnaryOperator<String> headerProcessing =
                (String text) -> "From Casper and Rishi: " + text;
        final UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("Udemy", "Guidemy");
        final Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing);
        final String result = pipeline.apply("The Udemy classes are the best to learn technology!!");
        assertEquals("From Casper and Rishi: The Guidemy classes are the best to learn technology!!", result);

    }

    @Test
    void testChainOfResponsibility() {
        final ProcessingObject<String> p1 = new HeaderTextProcessing();
        final ProcessingObject<String> p2 = new SpellChecker();
        p1.setSuccessor(p2);
        final String result = p1.handle("The Udemy classes are the best to learn technology!!");
        System.out.println(result);
    }
}
