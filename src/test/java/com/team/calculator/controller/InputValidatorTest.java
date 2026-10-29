package com.team.calculator.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest 
{

    private InputValidator validator;

    @BeforeEach
    public void setUp() 
  {
        validator = new InputValidator();
    }

    @Test
    public void testStartWithMultiplyOrDivide() 
  {
        assertFalse(validator.isValid("", "*"));
        assertFalse(validator.isValid("", "/"));
        assertTrue(validator.isValid("", "+"));
        assertTrue(validator.isValid("", "5"));
    }

    @Test
    public void testConsecutiveOperators() 
  {
        assertFalse(validator.isValid("5+", "+"));
        assertFalse(validator.isValid("5*", "*"));
        // Επιτρέπεται το αρνητικό πρόσημο μετά από τελεστή
        assertTrue(validator.isValid("5*", "-"));
    }

    @Test
    public void testMultipleDotsInSameNumber() 
  {
        assertTrue(validator.isValid("5", "."));
        assertFalse(validator.isValid("5.2", "."));
        assertFalse(validator.isValid("3,5", ","));
        // Μετά από πράξη, επιτρέπεται τελεία σε νέο αριθμό
        assertTrue(validator.isValid("5.2+", "3"));
        assertTrue(validator.isValid("5.2+3", "."));
    }

    @Test
    public void testUnmatchedClosingParenthesis() 
  {
        assertFalse(validator.isValid("", ")"));
        assertFalse(validator.isValid("5+3", ")"));
        assertTrue(validator.isValid("(5+3", ")"));
    }
}
