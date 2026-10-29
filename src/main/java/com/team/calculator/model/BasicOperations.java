package com.team.calculator.model;

/**
 * Οι μαθηματικές πράξεις του κομπιουτεράκι.
 * <p>
 * Η κλάση δεν διαβάζει από το πληκτρολόγιο και δεν εκτυπώνει τίποτα,
 * ώστε να μπορεί να ελεγχθεί εύκολα με unit tests.
 *
 * @author Όνομα Επώνυμο
 * @version 1.0
 */
public class BasicOperations {

    /**
     * Προσθέτει δύο αριθμούς.
     *
     * @param a ο πρώτος αριθμός
     * @param b ο δεύτερος αριθμός
     * @return το a + b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Αφαιρεί τον δεύτερο αριθμό από τον πρώτο.
     *
     * @param a ο πρώτος αριθμός
     * @param b ο δεύτερος αριθμός
     * @return το a - b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Πολλαπλασιάζει δύο αριθμούς.
     *
     * @param a ο πρώτος αριθμός
     * @param b ο δεύτερος αριθμός
     * @return το a * b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Διαιρεί τον πρώτο αριθμό με τον δεύτερο.
     *
     * @param a ο διαιρετέος
     * @param b ο διαιρέτης
     * @return το a / b
     * @throws CalculatorException αν ο διαιρέτης είναι 0
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new CalculatorException("Διαίρεση με το 0");
        }
        return a / b;
    }

    // ----- Από εδώ και κάτω: ο υπάρχων κώδικας, αμετάβλητος -----

    /**
     * Εκτελεί πράξη με δύο αριθμούς.
     *
     * @param a        ο πρώτος αριθμός
     * @param operator η πράξη: {@code +}, {@code -}, {@code *}, {@code /}
     *                 ή {@code ^} (το a στην b)
     * @param b        ο δεύτερος αριθμός
     * @return το αποτέλεσμα της πράξης
     * @throws ArithmeticException αν γίνεται διαίρεση με το 0
     *                             ή αν η πράξη δεν είναι γνωστή
     */
    public double calculate(double a, String operator, double b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Διαίρεση με το 0");
                }
                yield a / b;
            }
            case "^" -> Math.pow(a, b);
            default -> throw new ArithmeticException("Άγνωστη πράξη");
        };
    }

    /**
     * Υπολογίζει το τετράγωνο ενός αριθμού (x²).
     *
     * @param a ο αριθμός
     * @return το a * a
     */
    public double square(double a) {
        return a * a;
    }

    /**
     * Υπολογίζει την τετραγωνική ρίζα ενός αριθμού.
     *
     * @param a ο αριθμός (πρέπει να είναι μεγαλύτερος ή ίσος του 0)
     * @return η τετραγωνική ρίζα του a
     * @throws ArithmeticException αν ο αριθμός είναι αρνητικός
     */
    public double squareRoot(double a) {
        if (a < 0) {
            throw new ArithmeticException("Δεν υπάρχει ρίζα αρνητικού αριθμού");
        }
        return Math.sqrt(a);
    }

    /**
     * Μετατρέπει έναν αριθμό σε ποσοστό τοις εκατό (διαίρεση με το 100).
     *
     * @param a ο αριθμός
     * @return το a / 100 (π.χ. το 50 γίνεται 0.5)
     */
    public double percent(double a) {
        return a / 100;
    }
}