
package org.example.QuantityMeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    // Inner class representing a measurement in feet
    public static class Feet {

        // Encapsulated and immutable measurement value
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals() for value-based equality
        @Override
        public boolean equals(Object obj) {

            // Check if both references point to the same object
            if (this == obj) {
                return true;
            }

            // Check null and class type
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Type casting
            Feet feet = (Feet) obj;

            // Compare measurement values
            return Double.compare(this.value, feet.value) == 0;
        }

        // Maintain equals() and hashCode() contract
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    // Main method to demonstrate UC1
    public static void main(String[] args) {

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        System.out.println("Feet 1: 1.0 ft");
        System.out.println("Feet 2: 1.0 ft");

        System.out.println("Are measurements equal? "
                + feet1.equals(feet2));
    }
}