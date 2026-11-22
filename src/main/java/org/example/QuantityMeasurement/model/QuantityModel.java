
/*
 * Class Name  : QuantityModel
 * Description : Represents a typed measurement internally in the service layer.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.model;

import org.example.QuantityMeasurement.IMeasurable;

public class QuantityModel<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // Initializes a measurement with its value and unit.
    public QuantityModel(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Measurement unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    // Returns the measurement value.
    public double getValue() {
        return value;
    }

    // Returns the measurement unit.
    public U getUnit() {
        return unit;
    }

    // Converts the measurement into the base unit.
    public double getBaseValue() {
        return unit.convertToBaseUnit(value);
    }

    // Returns the measurement category.
    public String getMeasurementType() {
        return unit.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "QuantityModel{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}