package com.team.calculator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MemoryTest {

    @Test
    void initialValueIsZero() {
        Memory memory = new Memory();
        assertEquals(0, memory.recall());
    }

    @Test
    void addIncreasesValue() {
        Memory memory = new Memory();
        memory.add(5);
        memory.add(3);
        assertEquals(8, memory.recall());
    }

    @Test
    void subtractDecreasesValue() {
        Memory memory = new Memory();
        memory.add(10);
        memory.subtract(4);
        assertEquals(6, memory.recall());
    }

    @Test
    void clearResetsToZero() {
        Memory memory = new Memory();
        memory.add(7);
        memory.clear();
        assertEquals(0, memory.recall());
    }
}
