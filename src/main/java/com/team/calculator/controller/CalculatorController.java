package com.team.calculator.controller;

import com.team.calculator.model.CalculatorModel;
import com.team.calculator.view.CalculatorView;

public class CalculatorController {
    private final CalculatorView view;
    private final CalculatorModel model;
    private final InputValidator validator;

    public CalculatorController(CalculatorView view, CalculatorModel model, InputValidator validator) {
        this.view = view;
        this.model = model;
        this.validator = validator;
        view.setButtonListener(this::handleInput);
    }

    /** Καλείται για κάθε κουμπί που πατιέται. */
    public void handleInput(String token) {
        throw new UnsupportedOperationException("TODO");
    }
}