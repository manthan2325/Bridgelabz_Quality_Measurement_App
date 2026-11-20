
/*
 * Class Name  : QuantityWeight
 * Description : Represents weight measurements and supports
 *               equality, unit conversion, and addition.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Measurement must be finite");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double convertToKilograms() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit cannot be null");

        double valueInKilograms = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueInKilograms);

        if (!Double.isFinite(convertedValue)) {
            throw new ArithmeticException("Conversion result is out of range");
        }

        return new QuantityWeight(convertedValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityWeight weight = (QuantityWeight) obj;

        return Double.compare(
                this.convertToKilograms(),
                weight.convertToKilograms()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToKilograms());
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(this, other, this.unit);
    }

    public QuantityWeight add(
            QuantityWeight other,
            WeightUnit targetUnit) {

        return add(this, other, targetUnit);
    }

    public static QuantityWeight add(
            QuantityWeight weight1,
            QuantityWeight weight2) {

        if (weight1 == null) {
            throw new IllegalArgumentException("First measurement cannot be null");
        }

        return add(weight1, weight2, weight1.unit);
    }

    public static QuantityWeight add(
            QuantityWeight weight1,
            QuantityWeight weight2,
            WeightUnit targetUnit) {

        if (weight1 == null || weight2 == null) {
            throw new IllegalArgumentException("Measurements cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addInTargetUnit(weight1, weight2, targetUnit);
    }

    private static QuantityWeight addInTargetUnit(
            QuantityWeight weight1,
            QuantityWeight weight2,
            WeightUnit targetUnit) {

        double firstInKilograms = weight1.convertToKilograms();
        double secondInKilograms = weight2.convertToKilograms();

        double sumInKilograms = firstInKilograms + secondInKilograms;

        if (!Double.isFinite(sumInKilograms)) {
            throw new ArithmeticException("Addition result is out of range");
        }

        double result = targetUnit.convertFromBaseUnit(sumInKilograms);

        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Converted addition result is out of range");
        }

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}