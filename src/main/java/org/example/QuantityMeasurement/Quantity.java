
/*
 * Class Name  : Quantity
 * Description : Generic measurement class supporting arithmetic operations.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) {
                throw new ArithmeticException("Division by zero is not allowed");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        private double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

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

    private void validateArithmeticOperands(
            Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (unit.getClass() != other.unit.getClass()
                || (targetUnitRequired
                && unit.getClass() != targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }

        if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Measurement must be finite");
        }
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation,
            U targetUnit,
            boolean targetUnitRequired) {

        validateArithmeticOperands(other, targetUnit, targetUnitRequired);

        double firstBaseValue = convertToBaseUnit();
        double secondBaseValue = other.convertToBaseUnit();

        if (!Double.isFinite(firstBaseValue)
                || !Double.isFinite(secondBaseValue)) {
            throw new ArithmeticException("Base unit conversion result is out of range");
        }

        double result = operation.compute(firstBaseValue, secondBaseValue);

        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Arithmetic result is out of range");
        }

        return result;
    }

    private Quantity<U> createResult(double baseValue, U targetUnit) {
        double result = targetUnit.convertFromBaseUnit(baseValue);

        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Converted result is out of range");
        }

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }


    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        Objects.requireNonNull(other, "Other quantity cannot be null");

        double result = performBaseArithmetic(
                other, ArithmeticOperation.ADD, targetUnit, true);

        return createResult(result, targetUnit);
    }


    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        double result = performBaseArithmetic(
                other, ArithmeticOperation.SUBTRACT, targetUnit, true);
        return createResult(result, targetUnit);
    }

    public double divide(Quantity<U> other) {
        return performBaseArithmetic(
                other, ArithmeticOperation.DIVIDE, null, false);
    }

    public Quantity<U> convertTo(U targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit cannot be null");

        if (unit.getClass() != targetUnit.getClass()) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }

        return createResult(convertToBaseUnit(), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> quantity = (Quantity<?>) obj;

        if (unit.getClass() != quantity.unit.getClass()) return false;

        return Double.compare(
                this.convertToBaseUnit(),
                quantity.unit.convertToBaseUnit(quantity.value)
        ) == 0;
    }
    //getting the hashcode
    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), convertToBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}