
/*
 * Class Name  : WeightUnit
 * Description : Defines weight units and handles conversions
 *               to and from the base unit (kilogram).
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}