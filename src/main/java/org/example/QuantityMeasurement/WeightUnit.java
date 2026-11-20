
/*
 * Class Name  : WeightUnit
 * Description : Defines weight units and their conversions.
 * Author      : Utakarsh Jain
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}