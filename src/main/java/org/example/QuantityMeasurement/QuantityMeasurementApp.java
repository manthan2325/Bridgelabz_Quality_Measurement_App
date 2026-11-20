
/*
 * Class Name  : QuantityMeasurementApp
 * Description : Demonstrates quantity equality using QuantityLength.
 * Author      : Manthan Hanchate
 * Date        : 18/11/2026
 */

package org.example.QuantityMeasurement;

public class QuantityMeasurementApp {

    public static class Feet {
        private final QuantityLength quantity;

        public Feet(double value) {
            quantity = new QuantityLength(value, LengthUnit.FEET);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            return quantity.equals(((Feet) obj).quantity);
        }

        @Override
        public int hashCode() {
            return quantity.hashCode();
        }
    }

    public static class Inches {
        private final QuantityLength quantity;

        public Inches(double value) {
            quantity = new QuantityLength(value, LengthUnit.INCHES);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            return quantity.equals(((Inches) obj).quantity);
        }

        @Override
        public int hashCode() {
            return quantity.hashCode();
        }
    }

    public static boolean areFeetEqual(double value1, double value2) {
        return new QuantityLength(value1, LengthUnit.FEET)
                .equals(new QuantityLength(value2, LengthUnit.FEET));
    }

    public static boolean areInchesEqual(double value1, double value2) {
        return new QuantityLength(value1, LengthUnit.INCHES)
                .equals(new QuantityLength(value2, LengthUnit.INCHES));
    }

    public static boolean areFeetAndInchesEqual(
            double feet, double inches) {

        return new QuantityLength(feet, LengthUnit.FEET)
                .equals(new QuantityLength(inches, LengthUnit.INCHES));
    }

    public static void main(String[] args) {

        QuantityLength quantity1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength quantity2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength quantity3 =
                new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength quantity4 =
                new QuantityLength(1.0, LengthUnit.INCHES);

        System.out.println("1 Feet = 12 Inches: "
                + quantity1.equals(quantity2));

        System.out.println("1 Inch = 1 Inch: "
                + quantity3.equals(quantity4));
    }

    /**
     * Demonstrates conversion using raw measurement values.
     */
    public static double demonstrateLengthConversion(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit) {

        double result = QuantityLength.convert(
                value, sourceUnit, targetUnit);

        System.out.println(value + " " + sourceUnit
                + " = " + result + " " + targetUnit);

        return result;
    }

    /**
     * Demonstrates conversion using an existing QuantityLength object.
     */
    public static QuantityLength demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit targetUnit) {

        QuantityLength converted = quantity.convertTo(targetUnit);

        System.out.println(quantity + " = " + converted);

        return converted;
    }

    /**
     * Demonstrates equality between two measurements.
     */
    public static boolean demonstrateLengthEquality(
            QuantityLength quantity1,
            QuantityLength quantity2) {

        boolean result = quantity1.equals(quantity2);

        System.out.println(quantity1 + " and " + quantity2
                + " are equal: " + result);

        return result;
    }

    /**
     * Demonstrates conversion and comparison between measurements.
     */
    public static boolean demonstrateLengthComparison(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2) {

        QuantityLength quantity1 =
                new QuantityLength(value1, unit1);

        QuantityLength quantity2 =
                new QuantityLength(value2, unit2);

        return demonstrateLengthEquality(quantity1, quantity2);
    }

}