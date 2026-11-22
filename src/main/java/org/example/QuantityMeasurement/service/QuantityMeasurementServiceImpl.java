/*
 * Class Name  : QuantityMeasurementServiceImpl
 * Description : Implements measurement business logic and repository operations.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.service;

import org.example.QuantityMeasurement.IMeasurable;
import org.example.QuantityMeasurement.Quantity;
import org.example.QuantityMeasurement.dto.QuantityDTO;
import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;
import org.example.QuantityMeasurement.exception.QuantityMeasurementException;
import org.example.QuantityMeasurement.repository.IQuantityMeasurementRepository;

import java.util.List;
import java.util.Objects;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    // Injects the repository through constructor dependency injection.
    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        this.repository = Objects.requireNonNull(
                repository, "Repository cannot be null");
    }

    @Override
    public QuantityMeasurementEntity convert(
            QuantityDTO source, String targetUnit) {

        validateDTO(source);

        try {
            IMeasurable sourceUnit = IMeasurable.getUnit(source.getUnit());
            IMeasurable destinationUnit = IMeasurable.getUnit(targetUnit);

            validateMeasurementType(sourceUnit, destinationUnit);

            Quantity quantity = new Quantity(source.getValue(), sourceUnit);
            Quantity converted = quantity.convertTo(destinationUnit);

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity(
                            source.getValue(),
                            source.getUnit(),
                            "CONVERSION",
                            converted.getValue(),
                            targetUnit
                    );

            repository.save(entity);
            return entity;

        } catch (Exception exception) {
            throw handleException("Conversion failed", exception);
        }
    }

    @Override
    public QuantityMeasurementEntity compare(
            QuantityDTO first, QuantityDTO second) {

        validateDTO(first);
        validateDTO(second);

        try {
            IMeasurable firstUnit = IMeasurable.getUnit(first.getUnit());
            IMeasurable secondUnit = IMeasurable.getUnit(second.getUnit());

            validateMeasurementType(firstUnit, secondUnit);

            Quantity firstQuantity =
                    new Quantity(first.getValue(), firstUnit);

            Quantity secondQuantity =
                    new Quantity(second.getValue(), secondUnit);

            double result = firstQuantity.equals(secondQuantity) ? 1.0 : 0.0;

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity(
                            first.getValue(), first.getUnit(),
                            second.getValue(), second.getUnit(),
                            "COMPARE", result, "BOOLEAN"
                    );

            repository.save(entity);
            return entity;

        } catch (Exception exception) {
            throw handleException("Comparison failed", exception);
        }
    }


    @Override
    public QuantityMeasurementEntity calculate(
            QuantityDTO first, QuantityDTO second, String operation) {

        // Validates both measurement inputs.
        validateDTO(first);
        validateDTO(second);

        if (operation == null || operation.isBlank()) {
            throw new QuantityMeasurementException(
                    "Operation cannot be null or empty");
        }

        try {
            // Resolves measurement units.
            IMeasurable firstUnit = IMeasurable.getUnit(first.getUnit());
            IMeasurable secondUnit = IMeasurable.getUnit(second.getUnit());

            validateMeasurementType(firstUnit, secondUnit);

            // Creates measurement objects.
            Quantity quantity1 = new Quantity(first.getValue(), firstUnit);
            Quantity quantity2 = new Quantity(second.getValue(), secondUnit);

            double resultValue;
            String resultUnit;

            // Performs the requested arithmetic operation.
            switch (operation.trim().toUpperCase()) {

                case "ADD": {
                    Quantity result = quantity1.add(quantity2, firstUnit);
                    resultValue = result.getValue();
                    resultUnit = first.getUnit();
                    break;
                }

                case "SUBTRACT": {
                    Quantity result = quantity1.subtract(quantity2, firstUnit);
                    resultValue = result.getValue();
                    resultUnit = first.getUnit();
                    break;
                }

                case "DIVIDE": {
                    resultValue = quantity1.divide(quantity2);
                    resultUnit = "RATIO";
                    break;
                }

                default:
                    throw new QuantityMeasurementException(
                            "Unsupported operation: " + operation);
            }

            // Creates and saves the operation record.
            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity(
                            first.getValue(), first.getUnit(),
                            second.getValue(), second.getUnit(),
                            operation.trim().toUpperCase(),
                            resultValue,
                            resultUnit
                    );

            repository.save(entity);
            return entity;

        } catch (Exception exception) {
            throw handleException("Calculation failed", exception);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return repository.findAll();
    }

    @Override
    public QuantityMeasurementEntity getMeasurementById(String id) {
        if (id == null || id.isBlank()) {
            throw new QuantityMeasurementException(
                    "Measurement ID cannot be null or empty");
        }

        return repository.findById(id);
    }

    // Validates the incoming measurement DTO.
    private void validateDTO(QuantityDTO dto) {
        if (dto == null) {
            throw new QuantityMeasurementException(
                    "Measurement DTO cannot be null");
        }

        if (dto.getUnit() == null || dto.getUnit().isBlank()) {
            throw new QuantityMeasurementException(
                    "Measurement unit cannot be null or empty");
        }

        if (!Double.isFinite(dto.getValue())) {
            throw new QuantityMeasurementException(
                    "Measurement value must be finite");
        }
    }

    // Ensures both operands belong to the same measurement category.
    private void validateMeasurementType(
            IMeasurable first, IMeasurable second) {

        if (!first.getClass().equals(second.getClass())) {
            throw new QuantityMeasurementException(
                    "Incompatible measurement types");
        }
    }

    // Converts lower-level exceptions into application-specific exceptions.
    private QuantityMeasurementException handleException(
            String message, Exception exception) {

        if (exception instanceof QuantityMeasurementException) {
            return (QuantityMeasurementException) exception;
        }
        return new QuantityMeasurementException(
                message + ": " + exception.getMessage(), exception);
    }
}