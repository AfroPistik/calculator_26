package com.team.calculator;

import com.team.calculator.controller.CalculatorController;
import com.team.calculator.controller.InputValidator;
import com.team.calculator.model.CalculatorModel;
import com.team.calculator.model.ExpressionEvaluator;
import com.team.calculator.model.History;
import com.team.calculator.model.Memory;
import com.team.calculator.model.SimpleExpressionEvaluator;
import com.team.calculator.view.SwingCalculatorView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingCalculatorView view = new SwingCalculatorView();

            ExpressionEvaluator evaluator = new SimpleExpressionEvaluator();
            Memory memory = new Memory();
            History history = new History();
            CalculatorModel model = new CalculatorModel(evaluator, memory, history);

            InputValidator validator = new InputValidator();
            new CalculatorController(view, model, validator);
            view.setVisible(true);
        });
    }
}