
/*
 * Class Name  : QuantityMeasurementEntity
 * Description : Stores measurement operation details and its result or error.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.entity;

import java.io.Serializable;
import java.util.UUID;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final double firstValue;
    private final String firstUnit;
    private final Double secondValue;
    private final String secondUnit;
    private final String operation;
    private final Double result;
    private final String resultUnit;
    private final String errorMessage;

    // Creates an entity for a single measurement.
    public QuantityMeasurementEntity(double value, String unit) {
        this(UUID.randomUUID().toString(), value, unit,
                null, null, "CONVERSION", null, null, null);
    }

    // Creates an entity for a successful binary operation.
    public QuantityMeasurementEntity(
            double firstValue, String firstUnit,
            double secondValue, String secondUnit,
            String operation, double result, String resultUnit) {

        this(UUID.randomUUID().toString(), firstValue, firstUnit,
                secondValue, secondUnit, operation, result, resultUnit, null);
    }

    // Creates an entity containing an operation error.
    public QuantityMeasurementEntity(
            double firstValue, String firstUnit,
            Double secondValue, String secondUnit,
            String operation, String errorMessage) {

        this(UUID.randomUUID().toString(), firstValue, firstUnit,
                secondValue, secondUnit, operation, null, null, errorMessage);
    }

    // Initializes all entity fields.
    private QuantityMeasurementEntity(
            String id, double firstValue, String firstUnit,
            Double secondValue, String secondUnit,
            String operation, Double result, String resultUnit,
            String errorMessage) {

        this.id = id;
        this.firstValue = firstValue;
        this.firstUnit = firstUnit;
        this.secondValue = secondValue;
        this.secondUnit = secondUnit;
        this.operation = operation;
        this.result = result;
        this.resultUnit = resultUnit;
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public String getOperation() {
        return operation;
    }

    public Double getResult() {
        return result;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Indicates whether the operation completed successfully.
    public boolean isSuccessful() {
        return errorMessage == null;
    }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id='" + id + '\'' +
                ", firstValue=" + firstValue +
                ", firstUnit='" + firstUnit + '\'' +
                ", secondValue=" + secondValue +
                ", secondUnit='" + secondUnit + '\'' +
                ", operation='" + operation + '\'' +
                ", result=" + result +
                ", resultUnit='" + resultUnit + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
    // Creates an entity for a successful unit conversion.
    public QuantityMeasurementEntity(
            double value, String unit,
            String operation, double result, String resultUnit) {

        this(UUID.randomUUID().toString(), value, unit,
                null, null, operation, result, resultUnit, null);
    }
}