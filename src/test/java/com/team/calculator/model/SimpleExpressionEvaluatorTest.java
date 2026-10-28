package com.team.calculator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SimpleExpressionEvaluatorTest {

    private final SimpleExpressionEvaluator evaluator = new SimpleExpressionEvaluator();
    private static final double DELTA = 1e-9;

    @Test
    void simpleOperations() throws Exception {
        assertEquals(7, evaluator.evaluate("3+4"), DELTA);
        assertEquals(-1, evaluator.evaluate("3-4"), DELTA);
        assertEquals(12, evaluator.evaluate("3*4"), DELTA);
        assertEquals(2.5, evaluator.evaluate("5/2"), DELTA);
    }

    @Test
    void operatorPrecedence() throws Exception {
        assertEquals(7, evaluator.evaluate("3+4*(2-1)"), DELTA);
        assertEquals(14, evaluator.evaluate("2+3*4"), DELTA);
        assertEquals(1, evaluator.evaluate("10-6/2*3+0"), DELTA);
    }

    @Test
    void leftAssociativity() throws Exception {
        assertEquals(5, evaluator.evaluate("10-3-2"), DELTA);
        assertEquals(2, evaluator.evaluate("8/2/2"), DELTA);
    }

    @Test
    void parentheses() throws Exception {
        assertEquals(20, evaluator.evaluate("(2+3)*4"), DELTA);
        assertEquals(9, evaluator.evaluate("((1+2))*(1+2)"), DELTA);
    }

    @Test
    void negativeNumbers() throws Exception {
        assertEquals(-3, evaluator.evaluate("-3"), DELTA);
        assertEquals(5, evaluator.evaluate("3--2"), DELTA);
        assertEquals(-6, evaluator.evaluate("2*-3"), DELTA);
        assertEquals(-5, evaluator.evaluate("-(2+3)"), DELTA);
    }

    @Test
    void decimalNumbers() throws Exception {
        assertEquals(3.75, evaluator.evaluate("1.5+2.25"), DELTA);
        assertEquals(0.5, evaluator.evaluate(".25*2"), DELTA);
    }

    @Test
    void whitespaceIsIgnored() throws Exception {
        assertEquals(7, evaluator.evaluate(" 3 + 4 * ( 2 - 1 ) "), DELTA);
    }

    @Test
    void invalidExpressionsThrow() {
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("3++"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("(2+3"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("2+3)"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("*3"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("1.2.3+1"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("2 3"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("abc"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("()"));
    }

    @Test
    void emptyOrNullThrows() {
        assertThrows(CalculatorException.class, () -> evaluator.evaluate(""));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("   "));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate(null));
    }

    @Test
    void divisionByZeroThrows() {
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("1/0"));
        assertThrows(CalculatorException.class, () -> evaluator.evaluate("4/(2-2)"));
    }
}