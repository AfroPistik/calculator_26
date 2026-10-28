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
}
