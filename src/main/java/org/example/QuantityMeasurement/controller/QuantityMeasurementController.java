
/*
 * Class Name  : QuantityMeasurementController
 * Description : Handles measurement requests and delegates them to the service layer.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.controller;

import org.example.QuantityMeasurement.dto.QuantityDTO;
import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;
import org.example.QuantityMeasurement.exception.QuantityMeasurementException;
import org.example.QuantityMeasurement.service.IQuantityMeasurementService;

import java.util.List;
import java.util.Objects;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    // Injects the service through constructor dependency injection.
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = Objects.requireNonNull(
                service, "Service cannot be null");
    }

    // Handles measurement conversion requests.
    public QuantityMeasurementEntity convert(
            QuantityDTO source, String targetUnit) {

        validateRequest(source);
        validateTargetUnit(targetUnit);

        return service.convert(source, targetUnit);
    }

    // Handles measurement comparison requests.
    public QuantityMeasurementEntity compare(
            QuantityDTO first, QuantityDTO second) {

        validateRequest(first);
        validateRequest(second);

        return service.compare(first, second);
    }

    // Handles arithmetic operation requests.
    public QuantityMeasurementEntity calculate(
            QuantityDTO first, QuantityDTO second, String operation) {

        validateRequest(first);
        validateRequest(second);

        if (operation == null || operation.isBlank()) {
            throw new QuantityMeasurementException(
                    "Operation cannot be null or empty");
        }

        return service.calculate(first, second, operation);
    }

    // Retrieves all measurement records.
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return service.getAllMeasurements();
    }

    // Retrieves a measurement record using its ID.
    public QuantityMeasurementEntity getMeasurementById(String id) {
        if (id == null || id.isBlank()) {
            throw new QuantityMeasurementException(
                    "Measurement ID cannot be null or empty");
        }

        return service.getMeasurementById(id);
    }

    // Validates the incoming measurement request.
    private void validateRequest(QuantityDTO dto) {
        if (dto == null) {
            throw new QuantityMeasurementException(
                    "Measurement request cannot be null");
        }

        if (dto.getUnit() == null || dto.getUnit().isBlank()) {
            throw new QuantityMeasurementException(
                    "Measurement unit cannot be empty");
        }

        if (!Double.isFinite(dto.getValue())) {
            throw new QuantityMeasurementException(
                    "Measurement value must be finite");
        }
    }

    // Validates the requested conversion target.
    private void validateTargetUnit(String targetUnit) {
        if (targetUnit == null || targetUnit.isBlank()) {
            throw new QuantityMeasurementException(
                    "Target unit cannot be empty");
        }
    }
}