package com.team.calculator.view;

import java.util.List;
import java.util.function.Consumer;

public class SwingCalculatorView implements CalculatorView {
    @Override public void setDisplay(String text) { throw new UnsupportedOperationException("TODO"); }
    @Override public void showError(String message) { throw new UnsupportedOperationException("TODO"); }
    @Override public void showHistory(List<String> entries) { throw new UnsupportedOperationException("TODO"); }
    @Override public void setButtonListener(Consumer<String> listener) { throw new UnsupportedOperationException("TODO"); }
    @Override public void show() { throw new UnsupportedOperationException("TODO"); }
}