
/*
 * Class Name  : IMeasurable
 * Description : Defines the common contract for measurement units.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

public interface IMeasurable {

    SupportsArithmetic DEFAULT_ARITHMETIC_SUPPORT = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    default boolean supportsArithmetic() {
        return DEFAULT_ARITHMETIC_SUPPORT.isSupported();
    }

    default boolean supportsAddition() {
        return supportsArithmetic();
    }

    default boolean supportsSubtraction() {
        return supportsArithmetic();
    }

    default boolean supportsDivision() {
        return supportsArithmetic();
    }

    default void validateOperationSupport(String operation) {
        // Supported by default.
    }
    static String getMeasurementType(String unitName) {
        return getUnit(unitName).getClass().getSimpleName();
    }

    static IMeasurable getUnit(String unitName) {
        if (unitName == null || unitName.isBlank()) {
            throw new IllegalArgumentException("Unit name cannot be null or empty");
        }

        String normalizedName = unitName.trim().toUpperCase();

        try {
            return LengthUnit.valueOf(normalizedName);
        } catch (IllegalArgumentException ignored) {
        }

        try {
            return WeightUnit.valueOf(normalizedName);
        } catch (IllegalArgumentException ignored) {
        }

        try {
            return VolumeUnit.valueOf(normalizedName);
        } catch (IllegalArgumentException ignored) {
        }

        try {
            return TemperatureUnit.valueOf(normalizedName);
        } catch (IllegalArgumentException ignored) {
        }

        throw new IllegalArgumentException("Unsupported measurement unit: " + unitName);
    }
}