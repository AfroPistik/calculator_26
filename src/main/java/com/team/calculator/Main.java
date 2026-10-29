package com.team.calculator;

import com.team.calculator.view.SwingCalculatorView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingCalculatorView view = new SwingCalculatorView();
            view.setVisible(true);
        });
    }
}