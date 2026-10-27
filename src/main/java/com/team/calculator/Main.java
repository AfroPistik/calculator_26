package com.team.calculator;

import com.team.calculator.controller.CalculatorController;
import com.team.calculator.controller.InputValidator;
import com.team.calculator.model.*;
import com.team.calculator.view.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorView view = new SwingCalculatorView();
            CalculatorModel model = new CalculatorModel(
                    new SimpleExpressionEvaluator(), new Memory(), new History());
            new CalculatorController(view, model, new InputValidator());
            view.show();
        });
    }
}