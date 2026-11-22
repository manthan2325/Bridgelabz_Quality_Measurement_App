
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
}