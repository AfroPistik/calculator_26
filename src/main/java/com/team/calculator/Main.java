package com.team.calculator;

import com.team.calculator.view.CalculatorView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorView view = new CalculatorView();
            view.setVisible(true);
        });
    }
}