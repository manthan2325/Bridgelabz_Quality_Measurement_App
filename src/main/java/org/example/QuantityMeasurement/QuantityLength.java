
/*
 * Class Name  : QuantityLength
 * Description : Represents length measurements and supports
 *               equality and unit conversion.
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
}