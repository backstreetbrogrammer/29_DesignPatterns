package com.backstreetbrogrammer.behavioral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    void testStrategyPattern() {
        final Validator numericValidator = new Validator((String s) -> s.matches("\\d+"));
        assertFalse(numericValidator.isValid("aaaa"));

        final Validator lowerCaseValidator = new Validator((String s) -> s.matches("[a-z]+"));
        assertTrue(lowerCaseValidator.isValid("backstreetbrogrammer"));
    }
}
