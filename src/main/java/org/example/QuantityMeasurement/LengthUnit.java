
/*
 * Class Name  : LengthUnit
 * Description : Defines length units and handles conversions
 *               to and from the base unit (feet).
 * Author      : Manthan Hanchate
 * Date        : 18/11/2026
 */
package org.example.QuantityMeasurement;
public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.393701 / 12.0);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
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