
package com.team.calculator.model;

public class AdvancedOperations {

        // Μέθοδος για δύναμη (βάρος^εκθέτης, π.χ. 2^3 = 8)
        public double power(double base, double exponent) {
            return Math.pow(base, exponent);
        }

        // Μέθοδος για τετραγωνική ρίζα
        public double squareRoot(double number) {
            if (number < 0) {
                throw new CalculatorException("Cannot calculate square root of a negative number");
            }
            return Math.sqrt(number);
        }
    // Ποσοστό
    public double percent(double value) {
        return value / 100.0;
    }

    // Ημίτονο (σε μοίρες)
    public double sin(double degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    // Συνημίτονο (σε μοίρες)
    public double cos(double degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    // Εφαπτομένη (σε μοίρες, με έλεγχο για 90°, 270° κλπ.)
    public double tan(double degrees) {
        double normalized = Math.abs(degrees) % 360;
        if (Math.abs(normalized - 90) < 1e-9 || Math.abs(normalized - 270) < 1e-9) {
            throw new CalculatorException("Tangent for " + degrees + "° is undefined.");
        }
        return Math.tan(Math.toRadians(degrees));
    }
}

