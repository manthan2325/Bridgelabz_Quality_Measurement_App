
/*
 * Class Name  : QuantityLength
 * Description : Represents length measurements and supports
 *               equality, unit conversion and addition.
 * Author      : Manthan Hanchate
 * Date        : 18/11/2026
 */

package org.example.QuantityMeasurement;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Measurement must be finite");
        }

        this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToFeet() {
        return value * unit.getConversionFactor();
    }

    /**
     * Converts a value from one length unit to another.
     *
     * @param value measurement value
     * @param sourceUnit original unit
     * @param targetUnit required unit
     * @return converted measurement value
     * @throws IllegalArgumentException if value is not finite
     * @throws NullPointerException if either unit is null
     */
    public static double convert(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Measurement must be finite");
        }

        Objects.requireNonNull(sourceUnit, "Source unit cannot be null");
        Objects.requireNonNull(targetUnit, "Target unit cannot be null");

        double valueInFeet =
                value * sourceUnit.getConversionFactor();

        double convertedValue =
                valueInFeet / targetUnit.getConversionFactor();

        if (!Double.isFinite(convertedValue)) {
            throw new ArithmeticException("Conversion result is out of range");
        }

        return convertedValue;
    }

    /**
     * Converts this measurement into the target unit.
     *
     * @param targetUnit required unit
     * @return a new QuantityLength instance
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(value, unit, targetUnit);

        return new QuantityLength(convertedValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength quantity = (QuantityLength) obj;

        return Double.compare(
                this.convertToFeet(),
                quantity.convertToFeet()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToFeet());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    /**
     * Adds another measurement using this object's unit.
     * Preserves UC6 backward compatibility.
     *
     * @param other measurement to add
     * @return new QuantityLength containing the sum
     */
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    /**
     * Adds another measurement using the specified target unit.
     *
     * @param other measurement to add
     * @param targetUnit result unit
     * @return new QuantityLength containing the sum
     */
    public QuantityLength add(
            QuantityLength other,
            LengthUnit targetUnit) {

        return add(this, other, targetUnit);
    }

    /**
     * Adds two measurements and returns the result
     * in the explicitly specified target unit.
     *
     * @param length1 first measurement
     * @param length2 second measurement
     * @param targetUnit result unit
     * @return new QuantityLength containing the sum
     */
    public static QuantityLength add(
            QuantityLength length1,
            QuantityLength length2,
            LengthUnit targetUnit) {

        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException(
                    "Measurements cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
        }

        return addInTargetUnit(length1, length2, targetUnit);
    }

    /**
     * Adds raw measurement values using their respective units.
     *
     * @param value1 first measurement value
     * @param unit1 first measurement unit
     * @param value2 second measurement value
     * @param unit2 second measurement unit
     * @param targetUnit result unit
     * @return new QuantityLength containing the sum
     */
    public static QuantityLength add(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2,
            LengthUnit targetUnit) {

        QuantityLength length1 =
                new QuantityLength(value1, unit1);

        QuantityLength length2 =
                new QuantityLength(value2, unit2);

        return add(length1, length2, targetUnit);
    }

    /**
     * Internal utility that performs addition after
     * converting both measurements into the base unit (feet).
     *
     * @param length1 first measurement
     * @param length2 second measurement
     * @param targetUnit result unit
     * @return new QuantityLength in the target unit
     */
    private static QuantityLength addInTargetUnit(
            QuantityLength length1,
            QuantityLength length2,
            LengthUnit targetUnit) {

        double firstInFeet = length1.convertToFeet();
        double secondInFeet = length2.convertToFeet();

        double sumInFeet = firstInFeet + secondInFeet;

        if (!Double.isFinite(sumInFeet)) {
            throw new ArithmeticException(
                    "Addition result is out of range");
        }

        double result = sumInFeet
                / targetUnit.getConversionFactor();

        if (!Double.isFinite(result)) {
            throw new ArithmeticException(
                    "Converted result is out of range");
        }

        return new QuantityLength(result, targetUnit);
    }
}