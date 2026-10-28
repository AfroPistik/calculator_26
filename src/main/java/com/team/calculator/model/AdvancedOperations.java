
package com.team.calculator.model;

public class AdvancedOperations {

        // Μέθοδος για δύναμη (βάρος^εκθέτης, π.χ. 2^3 = 8)
        public double power(double base, double exponent) {
            return Math.pow(base, exponent);
        }

        // Μέθοδος για τετραγωνική ρίζα
        public double squareRoot(double number) {
            if (number < 0) {
                throw new IllegalArgumentException("Cannot calculate square root of a negative number");
            }
            return Math.sqrt(number);
        }
}

