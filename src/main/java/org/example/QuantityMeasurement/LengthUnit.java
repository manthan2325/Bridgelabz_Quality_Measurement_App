
/*
 * Class Name  : LengthUnit
 * Description : Defines supported length units and conversion factors.
 * Author      : Manthan Hanchate
 * Date        : 18/11/2026
 */

package org.example.QuantityMeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}