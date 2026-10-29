package com.team.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<String> entries = new ArrayList<>();

    public void add(String expression, double result) {
        entries.add(expression + " = " + result);
    }

    public List<String> getAll() {
        return new ArrayList<>(entries);
    }

    public void clear() {
        entries.clear();
    }
}
