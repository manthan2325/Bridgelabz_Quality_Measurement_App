
/*
 * Class Name  : QuantityMeasurementApp
 * Description : Implements UC1 and UC2 - Feet and Inches Measurement
 *               Equality. Supports same-unit equality and cross-unit
 *               comparison between Feet and Inches.
 * Author      : Manthan Hanchate
 * Date        : 17/11/2026
 */

package org.example.QuantityMeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    /**
     * Represents a measurement in Feet.
     */
    public static class Feet {

        // Immutable measurement value in feet.
        private final double value;

        /**
         * Initializes a Feet measurement.
         *
         * @param value measurement value
         */
        public Feet(double value) {
            this.value = value;
        }

        /**
         * Returns the measurement value in feet.
         *
         * @return feet value
         */
        public double getValue() {
            return value;
        }

        /**
         * Compares two Feet objects based on their values.
         *
         * @param obj object to compare
         * @return true if values are equal
         */
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet feet = (Feet) obj;

            return Double.compare(this.value, feet.value) == 0;
        }

        /**
         * Generates hash code using measurement value.
         *
         * @return hash code
         */
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * Represents a measurement in Inches.
     */
    public static class Inches {

        // Immutable measurement value in inches.
        private final double value;

        /**
         * Initializes an Inches measurement.
         *
         * @param value measurement value
         */
        public Inches(double value) {
            this.value = value;
        }

        /**
         * Returns the measurement value in inches.
         *
         * @return inches value
         */
        public double getValue() {
            return value;
        }

        /**
         * Compares two Inches objects based on their values.
         *
         * @param obj object to compare
         * @return true if values are equal
         */
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Inches inches = (Inches) obj;

            return Double.compare(this.value, inches.value) == 0;
        }

        /**
         * Generates hash code using measurement value.
         *
         * @return hash code
         */
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * Checks equality between two Feet measurements.
     *
     * @param value1 first feet value
     * @param value2 second feet value
     * @return true if both values are equal
     */
    public static boolean areFeetEqual(double value1, double value2) {

        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);

        return feet1.equals(feet2);
    }

    /**
     * Checks equality between two Inches measurements.
     *
     * @param value1 first inches value
     * @param value2 second inches value
     * @return true if both values are equal
     */
    public static boolean areInchesEqual(double value1, double value2) {

        Inches inches1 = new Inches(value1);
        Inches inches2 = new Inches(value2);

        return inches1.equals(inches2);
    }

    /**
     * Checks equality between Feet and Inches measurements.
     * One foot is equivalent to twelve inches.
     *
     * @param feetValue measurement in feet
     * @param inchesValue measurement in inches
     * @return true if both measurements are equal
     */
    public static boolean areFeetAndInchesEqual(
            double feetValue, double inchesValue) {

        double convertedFeetToInches = feetValue * 12.0;

        return Double.compare(convertedFeetToInches, inchesValue) == 0;
    }

    /**
     * Main method demonstrating UC1 and UC2.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // UC1: Feet equality.
        System.out.println("Feet Equality: "
                + areFeetEqual(1.0, 1.0));

        // UC2: Inches equality.
        System.out.println("Inches Equality: "
                + areInchesEqual(1.0, 1.0));

        // UC2: Feet-to-Inches equality.
        System.out.println("Feet-Inches Equality: "
                + areFeetAndInchesEqual(1.0, 12.0));
    }
}