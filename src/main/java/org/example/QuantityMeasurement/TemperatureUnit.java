
/*
 * Class Name  : TemperatureUnit
 * Description : Defines temperature units and conversion formulas.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(
            1.0,
            celsius -> celsius,
            kelvin -> kelvin - 273.15
    ),

    FAHRENHEIT(
            5.0 / 9.0,
            fahrenheit -> (fahrenheit - 32.0) * 5.0 / 9.0,
            kelvin -> (kelvin - 273.15) * 9.0 / 5.0 + 32.0
    ),

    KELVIN(
            1.0,
            kelvin -> kelvin - 273.15,
            kelvin -> kelvin
    );

    private final double conversionFactor;
    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromKelvin;

    TemperatureUnit(
            double conversionFactor,
            Function<Double, Double> toCelsius,
            Function<Double, Double> fromKelvin) {
        this.conversionFactor = conversionFactor;
        this.toCelsius = toCelsius;
        this.fromKelvin = fromKelvin;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        double celsius = toCelsius.apply(value);
        return celsius + 273.15;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromKelvin.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation.toLowerCase()
                        + " operation on absolute temperature values"
        );
    }
}