
/*
 * Class Name  : QuantityMeasurementServiceImplTest
 * Description : Tests UC15 measurement service operations.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.service;

import org.example.QuantityMeasurement.dto.QuantityDTO;
import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;
import org.example.QuantityMeasurement.exception.QuantityMeasurementException;
import org.example.QuantityMeasurement.repository.IQuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementServiceImplTest {

    private QuantityMeasurementServiceImpl service;
    private TestRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TestRepository();
        service = new QuantityMeasurementServiceImpl(repository);
    }

    // Tests conversion between compatible units.
    @Test
    void testLengthConversion() {
        QuantityMeasurementEntity result =
                service.convert(new QuantityDTO(1.0, "FEET"), "INCHES");

        assertEquals(12.0, result.getResult(), 0.000001);
        assertEquals("INCHES", result.getResultUnit());
        assertTrue(result.isSuccessful());
    }

    // Tests comparison of equivalent measurements.
    @Test
    void testMeasurementComparison() {
        QuantityMeasurementEntity result =
                service.compare(
                        new QuantityDTO(1.0, "FEET"),
                        new QuantityDTO(12.0, "INCHES")
                );

        assertEquals(1.0, result.getResult());
    }

    // Tests addition of compatible measurements.
    @Test
    void testAddition() {
        QuantityMeasurementEntity result =
                service.calculate(
                        new QuantityDTO(1.0, "FEET"),
                        new QuantityDTO(12.0, "INCHES"),
                        "ADD"
                );

        assertEquals(2.0, result.getResult(), 0.000001);
        assertEquals("FEET", result.getResultUnit());
    }

    // Tests subtraction of compatible measurements.
    @Test
    void testSubtraction() {
        QuantityMeasurementEntity result =
                service.calculate(
                        new QuantityDTO(2.0, "FEET"),
                        new QuantityDTO(12.0, "INCHES"),
                        "SUBTRACT"
                );

        assertEquals(1.0, result.getResult(), 0.000001);
    }

    // Tests division of compatible measurements.
    @Test
    void testDivision() {
        QuantityMeasurementEntity result =
                service.calculate(
                        new QuantityDTO(10.0, "FEET"),
                        new QuantityDTO(2.0, "FEET"),
                        "DIVIDE"
                );

        assertEquals(5.0, result.getResult(), 0.000001);
        assertEquals("RATIO", result.getResultUnit());
    }

    // Tests rejection of incompatible measurement categories.
    @Test
    void testIncompatibleMeasurementTypes() {
        assertThrows(
                QuantityMeasurementException.class,
                () -> service.compare(
                        new QuantityDTO(1.0, "FEET"),
                        new QuantityDTO(1.0, "KILOGRAM")
                )
        );
    }

    // Tests rejection of unsupported arithmetic operations.
    @Test
    void testUnsupportedOperation() {
        assertThrows(
                QuantityMeasurementException.class,
                () -> service.calculate(
                        new QuantityDTO(1.0, "FEET"),
                        new QuantityDTO(2.0, "FEET"),
                        "MULTIPLY"
                )
        );
    }

    // Tests null measurement validation.
    @Test
    void testNullMeasurement() {
        assertThrows(
                QuantityMeasurementException.class,
                () -> service.convert(null, "INCHES")
        );
    }

    // Tests repository record creation.
    @Test
    void testRepositorySave() {
        service.convert(new QuantityDTO(1.0, "FEET"), "INCHES");

        assertEquals(1, service.getAllMeasurements().size());
    }

    // Provides isolated in-memory repository storage for testing.
    private static class TestRepository
            implements IQuantityMeasurementRepository {

        private final List<QuantityMeasurementEntity> records =
                new ArrayList<>();

        @Override
        public void save(QuantityMeasurementEntity entity) {
            records.add(entity);
        }

        @Override
        public List<QuantityMeasurementEntity> findAll() {
            return new ArrayList<>(records);
        }

        @Override
        public QuantityMeasurementEntity findById(String id) {
            return records.stream()
                    .filter(entity -> entity.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public void clear() {
            records.clear();
        }
    }
}