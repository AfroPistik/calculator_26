package com.team.calculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdvancedPanel extends JPanel {
    private JButton[] buttons;
    private final String[] buttonLabels = {"^", "√", "%", "sin", "cos", "tan"};

    public AdvancedPanel() {
        setLayout(new GridLayout(2, 3, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Advanced Operations"));

        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setActionCommand(buttonLabels[i]);
            // Εναλλακτικά, αν θες να ταιριάζει με το σκούρο στυλ του Issue 5:
            buttons[i].setBackground(new Color(51, 51, 51));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);

            add(buttons[i]);
        }
    }

    public void addActionListener(ActionListener listener) {
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
    }
}