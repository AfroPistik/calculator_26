package com.team.calculator.model;

public interface ExpressionEvaluator {
    /** Υπολογίζει π.χ. "3+4*(2-1)" με σωστή προτεραιότητα και παρενθέσεις. */
    double evaluate(String expression) throws CalculatorException;
}