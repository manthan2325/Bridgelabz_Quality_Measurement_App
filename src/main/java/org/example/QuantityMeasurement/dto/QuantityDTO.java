
/*
 * Class Name  : QuantityDTO
 * Description : Transfers measurement data between application layers.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.dto;

import org.example.QuantityMeasurement.IMeasurable;

public class QuantityDTO {

    private double value;
    private String unit;
    private String measurementType;

    public QuantityDTO() {
    }

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
        this.measurementType = IMeasurable.getMeasurementType(unit);
    }

    public QuantityDTO(double value, String measurementType, String unit) {
        this.value = value;
        this.measurementType = measurementType;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
        this.measurementType = IMeasurable.getMeasurementType(unit);
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    @Override
    public String toString() {
        return "QuantityDTO{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", measurementType='" + measurementType + '\'' +
                '}';
    }
}