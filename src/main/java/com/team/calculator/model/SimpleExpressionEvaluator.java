package com.team.calculator.model;

/**
 * Υπολογίζει αριθμητικές εκφράσεις με +, -, *, /, παρενθέσεις,
 * αρνητικούς και δεκαδικούς αριθμούς.
 *
 * Γραμματική (recursive descent):
 *   expression := term (('+' | '-') term)*
 *   term       := factor (('*' | '/') factor)*
 *   factor     := '-' factor | number | '(' expression ')'
 */
public class SimpleExpressionEvaluator implements ExpressionEvaluator {

    @Override
    public double evaluate(String expression) throws CalculatorException {
        if (expression == null || expression.trim().isEmpty()) {
            throw new CalculatorException("Κενή έκφραση");
        }
        Parser parser = new Parser(expression);
        double result = parser.parseExpression();
        parser.skipSpaces();
        if (!parser.atEnd()) {
            throw new CalculatorException(
                    "Μη αναμενόμενος χαρακτήρας '" + parser.peek() + "' στη θέση " + parser.pos);
        }
        return result;
    }

    // TODO: όταν ολοκληρωθεί το Issue 1, αντικατάστησε αυτές τις πράξεις με BasicOperations.
    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) throws CalculatorException {
        if (b == 0) {
            throw new CalculatorException("Διαίρεση με το μηδέν");
        }
        return a / b;
    }

    private static final class Parser {
        private final String s;
        private int pos = 0;

        Parser(String s) {
            this.s = s;
        }

        boolean atEnd() {
            return pos >= s.length();
        }

        char peek() {
            return s.charAt(pos);
        }

        void skipSpaces() {
            while (!atEnd() && Character.isWhitespace(peek())) {
                pos++;
            }
        }

        double parseExpression() throws CalculatorException {
            double value = parseTerm();
            while (true) {
                skipSpaces();
                if (atEnd()) return value;
                char c = peek();
                if (c == '+') {
                    pos++;
                    value = add(value, parseTerm());
                } else if (c == '-') {
                    pos++;
                    value = subtract(value, parseTerm());
                } else {
                    return value;
                }
            }
        }

        double parseTerm() throws CalculatorException {
            double value = parseFactor();
            while (true) {
                skipSpaces();
                if (atEnd()) return value;
                char c = peek();
                if (c == '*') {
                    pos++;
                    value = multiply(value, parseFactor());
                } else if (c == '/') {
                    pos++;
                    value = divide(value, parseFactor());
                } else {
                    return value;
                }
            }
        }

        double parseFactor() throws CalculatorException {
            skipSpaces();
            if (atEnd()) {
                throw new CalculatorException("Η έκφραση τελειώνει απροσδόκητα");
            }
            char c = peek();
            if (c == '-') {
                pos++;
                return -parseFactor();
            }
            if (c == '(') {
                pos++;
                double value = parseExpression();
                skipSpaces();
                if (atEnd() || peek() != ')') {
                    throw new CalculatorException("Λείπει η δεξιά παρένθεση");
                }
                pos++;
                return value;
            }
            return parseNumber();
        }

        double parseNumber() throws CalculatorException {
            int start = pos;
            boolean seenDot = false;
            boolean seenDigit = false;
            while (!atEnd()) {
                char c = peek();
                if (Character.isDigit(c)) {
                    seenDigit = true;
                } else if (c == '.' && !seenDot) {
                    seenDot = true;
                } else {
                    break;
                }
                pos++;
            }
            if (!seenDigit) {
                throw new CalculatorException(
                        "Αναμενόταν αριθμός στη θέση " + start);
            }
            return Double.parseDouble(s.substring(start, pos));
        }
    }
}