package com.team.calculator.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class SwingCalculatorView extends JFrame implements CalculatorView {

    private JLabel display;
    private JPanel buttonPanel;
    private Consumer<String> buttonListener;

    public SwingCalculatorView() {
        setTitle("iOS Calculator");
        setSize(360, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        display = new JLabel("0", JLabel.RIGHT);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Arial", Font.PLAIN, 65));
        display.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        mainPanel.add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Σειρά κουμπιών iOS
        String[] buttons = {
                "Del", "AC", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "±", "0", ",", "="
        };

        // Δημιουργία και χρωματισμός κουμπιών
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setOpaque(true);

            if (text.equals("÷") || text.equals("×") || text.equals("-") || text.equals("+") || text.equals("=")) {
                btn.setBackground(new Color(255, 149, 0)); // Πορτοκαλι
                btn.setForeground(Color.WHITE);
            } else if (text.equals("⌫") || text.equals("AC") || text.equals("%")) {
                btn.setBackground(new Color(165, 165, 165)); // Ανοιχτό γκρι
                btn.setForeground(Color.BLACK);
            } else {
                btn.setBackground(new Color(51, 51, 51)); // Σκούρο γκρι
                btn.setForeground(Color.WHITE);
            }

            btn.addActionListener(e -> {
                if (buttonListener != null) buttonListener.accept(text);
            });

            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    @Override
    public void setDisplay(String text) {
        display.setText(text);
    }

    @Override
    public void showError(String message) {
        display.setText(message);
    }

    @Override
    public void showHistory(List<String> entries) {
        // προς το παρόν κενό
    }

    @Override
    public void setButtonListener(Consumer<String> listener) {
        this.buttonListener = listener;
    }
}