package com.team.calculator.view;

import java.util.List;
import java.util.function.Consumer;

public interface CalculatorView {
    void setDisplay(String text);
    void showError(String message);
    void showHistory(List<String> entries);
    /** Δέχεται το κείμενο του κουμπιού που πατήθηκε, π.χ. "7", "+", "=", "C", "M+". */
    void setButtonListener(Consumer<String> listener);
    void show();
}