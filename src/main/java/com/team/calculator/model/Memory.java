package com.team.calculator.model;

public class Memory {
    private double value = 0;

    public void add(double x) {
        value += x;
    }

    public void subtract(double x) {
        value -= x;
    }

    public double recall() {
        return value;
    }

    public void clear() {
        value = 0;
    }
}
