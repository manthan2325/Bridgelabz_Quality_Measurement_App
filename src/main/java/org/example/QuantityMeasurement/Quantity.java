
/*
 * Class Name  : Quantity
 * Description : Generic measurement class supporting equality,
 *               conversion, and addition for different unit categories.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
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

    public U getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit cannot be null");

        if (unit.getClass() != targetUnit.getClass()) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        if (!Double.isFinite(convertedValue)) {
            throw new ArithmeticException("Conversion result is out of range");
        }

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        Objects.requireNonNull(other, "Other quantity cannot be null");
        Objects.requireNonNull(targetUnit, "Target unit cannot be null");

        if (unit.getClass() != other.unit.getClass()
                || unit.getClass() != targetUnit.getClass()) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }

        double firstBaseValue = this.convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        double sumInBaseUnit = firstBaseValue + secondBaseValue;

        if (!Double.isFinite(sumInBaseUnit)) {
            throw new ArithmeticException("Addition result is out of range");
        }

        double result = targetUnit.convertFromBaseUnit(sumInBaseUnit);

        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Converted addition result is out of range");
        }

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Quantity<?> quantity = (Quantity<?>) obj;

        if (unit.getClass() != quantity.unit.getClass()) {
            return false;
        }

        return Double.compare(
                this.convertToBaseUnit(),
                quantity.unit.convertToBaseUnit(quantity.value)
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), convertToBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}