
/*
 * Class Name  : QuantityLength
 * Description : Represents length measurements using a generic
 *               value and unit, with centralized equality logic.
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
}