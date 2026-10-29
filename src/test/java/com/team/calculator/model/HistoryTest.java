package com.team.calculator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class HistoryTest {

    @Test
    void newHistoryIsEmpty() {
        History history = new History();
        assertTrue(history.getAll().isEmpty());
    }

    @Test
    void addStoresEntry() {
        History history = new History();
        history.add("3+4", 7);
        assertEquals("3+4 = 7.0", history.getAll().get(0));
    }

    @Test
    void getAllKeepsInsertionOrder() {
        History history = new History();
        history.add("1+1", 2);
        history.add("2*3", 6);
        List<String> all = history.getAll();
        assertEquals(2, all.size());
        assertEquals("1+1 = 2.0", all.get(0));
        assertEquals("2*3 = 6.0", all.get(1));
    }

    @Test
    void getAllReturnsCopy() {
        History history = new History();
        history.add("1+1", 2);
        history.getAll().clear();
        assertEquals(1, history.getAll().size());
    }

    @Test
    void clearEmptiesHistory() {
        History history = new History();
        history.add("1+1", 2);
        history.clear();
        assertTrue(history.getAll().isEmpty());
    }
}
