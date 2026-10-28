package com.team.calculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedOperationsTest {

    private final AdvancedOperations advancedOperations = new AdvancedOperations();

    @Test
    public void testPower() {
        assertEquals(8.0, advancedOperations.power(2.0, 3.0), 0.0001);
    }

    @Test
    public void testSquareRoot() {
        assertEquals(4.0, advancedOperations.squareRoot(16.0), 0.0001);
    }
    @Test
    public void testSquareRootNegativeThrowsException() {
        assertThrows(CalculatorException.class, () -> advancedOperations.squareRoot(-1));
    }

    @Test
    public void testPercent() {
        assertEquals(0.5, advancedOperations.percent(50.0), 0.0001);
    }

    @Test
    public void testSin() {
        assertEquals(1.0, advancedOperations.sin(90.0), 0.0001);
        assertEquals(0.0, advancedOperations.sin(0.0), 0.0001);
    }

    @Test
    public void testCos() {
        assertEquals(1.0, advancedOperations.cos(0.0), 0.0001);
        assertEquals(0.0, advancedOperations.cos(90.0), 0.0001);
    }

    @Test
    public void testTan() {
        assertEquals(1.0, advancedOperations.tan(45.0), 0.0001);
    }

    @Test
    public void testTan90ThrowsException() {
        assertThrows(CalculatorException.class, () -> advancedOperations.tan(90.0));
    }
}
