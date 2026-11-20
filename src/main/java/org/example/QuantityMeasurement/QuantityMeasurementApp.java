
/*
 * Class Name  : QuantityMeasurementApp
 * Description : Demonstrates generic and legacy quantity operations.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    // UC10: Generic demonstration methods

    public static boolean demonstrateEquality(
            Quantity<?> quantity1,
            Quantity<?> quantity2) {

        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2,
            U targetUnit) {

        return quantity1.add(quantity2, targetUnit);
    }

    // UC1 and UC2: Legacy equality methods

    public static boolean areFeetEqual(double value1, double value2) {
        return Double.compare(value1, value2) == 0;
    }

    public static boolean areInchesEqual(double value1, double value2) {
        return Double.compare(value1, value2) == 0;
    }

    public static boolean areFeetAndInchesEqual(
            double feetValue, double inchesValue) {

        return Double.compare(
                LengthUnit.FEET.convertToBaseUnit(feetValue),
                LengthUnit.INCHES.convertToBaseUnit(inchesValue)
        ) == 0;
    }

    // UC5: Legacy length conversion and comparison methods

    public static double demonstrateLengthConversion(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit) {

        return QuantityLength.convert(value, sourceUnit, targetUnit);
    }

    public static QuantityLength demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    public static boolean demonstrateLengthComparison(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2) {

        return new QuantityLength(value1, unit1)
                .equals(new QuantityLength(value2, unit2));
    }

    // UC1: Legacy Feet class

    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet feet = (Feet) obj;
            return Double.compare(value, feet.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    // UC2: Legacy Inches class

    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches inches = (Inches) obj;
            return Double.compare(value, inches.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<WeightUnit> kilogram =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Length Operations:");
        System.out.println("Equality: " + demonstrateEquality(feet, inches));
        System.out.println("Conversion: "
                + demonstrateConversion(feet, LengthUnit.INCHES));
        System.out.println("Addition: "
                + demonstrateAddition(feet, inches, LengthUnit.FEET));

        System.out.println("\nWeight Operations:");
        System.out.println("Equality: "
                + demonstrateEquality(kilogram, grams));
        System.out.println("Conversion: "
                + demonstrateConversion(kilogram, WeightUnit.GRAM));
        System.out.println("Addition: "
                + demonstrateAddition(kilogram, grams, WeightUnit.KILOGRAM));
    }
}