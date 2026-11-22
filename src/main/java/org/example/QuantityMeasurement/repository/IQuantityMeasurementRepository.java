
/*
 * Interface Name : IQuantityMeasurementRepository
 * Description    : Defines the contract for storing and retrieving measurement records.
 * Author         : Manthan Hanchate
 * Date           : 19/11/2026
 */
package org.example.QuantityMeasurement.repository;

import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    // Saves a measurement operation record.
    void save(QuantityMeasurementEntity entity);

    // Retrieves all stored measurement records.
    List<QuantityMeasurementEntity> findAll();

    // Retrieves a record using its unique identifier.
    QuantityMeasurementEntity findById(String id);

    // Removes all stored measurement records.
    void clear();
}