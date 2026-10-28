package com.team.calculator.model;

public interface ExpressionEvaluator {
    double evaluate(String expression) throws CalculatorException;
}
