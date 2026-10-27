package com.team.calculator.model;

import java.util.List;

public class CalculatorModel {
    private final ExpressionEvaluator evaluator;
    private final Memory memory;
    private final History history;

    public CalculatorModel(ExpressionEvaluator evaluator, Memory memory, History history) {
        this.evaluator = evaluator;
        this.memory = memory;
        this.history = history;
    }

    public double calculate(String expression) throws CalculatorException {
        double result = evaluator.evaluate(expression);
        history.add(expression, result);
        return result;
    }

    public void memoryAdd(double v) { memory.add(v); }
    public void memorySubtract(double v) { memory.subtract(v); }
    public double memoryRecall() { return memory.recall(); }
    public void memoryClear() { memory.clear(); }
    public List<String> getHistory() { return history.getAll(); }
}