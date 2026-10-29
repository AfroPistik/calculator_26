package com.team.calculator.controller;

import com.team.calculator.model.CalculatorException;
import com.team.calculator.model.CalculatorModel;
import com.team.calculator.view.CalculatorView;

public class CalculatorController {
    private final CalculatorView view;
    private final CalculatorModel model;
    private final InputValidator validator;

    /** Η τρέχουσα έκφραση, π.χ. "3+4*(2-1)". */
    private final StringBuilder expression = new StringBuilder();

    /** true όταν η έκφραση είναι αποτέλεσμα (μετά από "=" ή μνήμη). */
    private boolean showingResult = false;

    public CalculatorController(CalculatorView view, CalculatorModel model, InputValidator validator) {
        this.view = view;
        this.model = model;
        this.validator = validator;
        view.setButtonListener(this::handleInput);
    }

    /** Καλείται για κάθε κουμπί που πατιέται. */
    public void handleInput(String rawToken) {
        if (rawToken == null || rawToken.isBlank()) {
            return;
        }
        String token = normalize(rawToken.trim());

        try {
            switch (token) {
                case "C" -> clear();
                case "⌫" -> backspace();
                case "=" -> evaluate();
                case "M+" -> memoryAdd();
                case "M-" -> memorySubtract();
                case "MR" -> memoryRecall();
                case "MC" -> model.memoryClear();
                default -> appendToken(token);
            }
        } catch (CalculatorException e) {
            showFailure(e.getMessage());
        } catch (RuntimeException e) {
            // π.χ. UnsupportedOperationException από κομμάτι που δεν έχει υλοποιηθεί ακόμα
            showFailure("Σφάλμα: η λειτουργία δεν είναι διαθέσιμη");
        }
    }

    // ---------- Ενέργειες ----------

    private void appendToken(String token) {
        // Αν μόλις εμφανίστηκε αποτέλεσμα και ο χρήστης γράψει αριθμό, ξεκινά νέος υπολογισμός.
        boolean startsNew = showingResult && startsOperand(token);
        String current = startsNew ? "" : expression.toString();

        if (!validator.isValid(current, token)) {
            return; // μη επιτρεπτό κουμπί, αγνοείται
        }
        if (startsNew) {
            expression.setLength(0);
        }
        showingResult = false;
        expression.append(token);
        updateDisplay();
    }

    private void clear() {
        expression.setLength(0);
        showingResult = false;
        updateDisplay();
    }

    private void backspace() {
        if (expression.length() > 0) {
            expression.setLength(expression.length() - 1);
        }
        showingResult = false;
        updateDisplay();
    }

    private void evaluate() throws CalculatorException {
        if (expression.length() == 0) {
            return;
        }
        double result = model.calculate(expression.toString());
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new CalculatorException("Μη έγκυρο αποτέλεσμα");
        }
        String text = format(result);
        expression.setLength(0);
        expression.append(text);
        showingResult = true;
        view.setDisplay(text);
        view.showHistory(model.getHistory());
    }

    private void memoryAdd() throws CalculatorException {
        model.memoryAdd(currentValue());
        showingResult = true;
    }

    private void memorySubtract() throws CalculatorException {
        model.memorySubtract(currentValue());
        showingResult = true;
    }

    private void memoryRecall() {
        String text = format(model.memoryRecall());
        boolean continuesExpression = !showingResult
                && expression.length() > 0
                && isOperatorOrOpenParen(expression.charAt(expression.length() - 1));
        if (continuesExpression) {
            expression.append(text);
            showingResult = false;
        } else {
            expression.setLength(0);
            expression.append(text);
            showingResult = true;
        }
        updateDisplay();
    }

    // ---------- Βοηθητικά ----------

    /** Η τιμή που θα πάει στη μνήμη: η τρέχουσα έκφραση, αν είναι απλός αριθμός. */
    private double currentValue() throws CalculatorException {
        if (expression.length() == 0) {
            return 0.0;
        }
        try {
            return Double.parseDouble(expression.toString());
        } catch (NumberFormatException e) {
            throw new CalculatorException("Πάτησε πρώτα = για να πάρεις αριθμό");
        }
    }

    private void showFailure(String message) {
        expression.setLength(0);
        showingResult = false;
        view.showError(message);
    }

    private void updateDisplay() {
        view.setDisplay(expression.length() == 0 ? "0" : expression.toString());
    }

    /** Μετατρέπει τα σύμβολα του View στα σύμβολα που καταλαβαίνει ο evaluator. */
    private String normalize(String token) {
        return switch (token) {
            case "AC" -> "C";
            case "÷" -> "/";
            case "×", "x" -> "*";
            case "−" -> "-";
            default -> token;
        };
    }

    private boolean startsOperand(String token) {
        char c = token.charAt(0);
        return Character.isDigit(c) || c == '.' || c == '(';
    }

    private boolean isOperatorOrOpenParen(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(';
    }

    private String format(double value) {
        if (value == Math.rint(value) && Math.abs(value) < 1e15) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}