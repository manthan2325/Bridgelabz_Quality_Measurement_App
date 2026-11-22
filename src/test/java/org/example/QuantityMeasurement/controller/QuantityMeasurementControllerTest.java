
/*
 * Class Name  : QuantityMeasurementControllerTest
 * Description : Tests UC15 controller validation and service delegation.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.controller;

import org.example.QuantityMeasurement.dto.QuantityDTO;
import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;
import org.example.QuantityMeasurement.exception.QuantityMeasurementException;
import org.example.QuantityMeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementControllerTest {

    private QuantityMeasurementController controller;
    private TestService service;

    @BeforeEach
    void setUp() {
        service = new TestService();
        controller = new QuantityMeasurementController(service);
    }

    // Tests conversion request delegation.
    @Test
    void testConvertDelegation() {
        controller.convert(new QuantityDTO(1.0, "FEET"), "INCHES");

        assertEquals(1, service.convertCalls);
    }

    // Tests comparison request delegation.
    @Test
    void testCompareDelegation() {
        controller.compare(
                new QuantityDTO(1.0, "FEET"),
                new QuantityDTO(12.0, "INCHES")
        );

        assertEquals(1, service.compareCalls);
    }

    // Tests calculation request delegation.
    @Test
    void testCalculateDelegation() {
        controller.calculate(
                new QuantityDTO(2.0, "FEET"),
                new QuantityDTO(1.0, "FEET"),
                "ADD"
        );

        assertEquals(1, service.calculateCalls);
    }

    // Tests retrieval of all measurement records.
    @Test
    void testGetAllMeasurements() {
        assertNotNull(controller.getAllMeasurements());
        assertEquals(1, service.getAllCalls);
    }

    // Tests retrieval of a measurement by ID.
    @Test
    void testGetMeasurementById() {
        controller.getMeasurementById("test-id");

        assertEquals(1, service.getByIdCalls);
    }

    // Tests rejection of a null measurement DTO.
    @Test
    void testNullDTOValidation() {
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.convert(null, "INCHES")
        );
    }

    // Tests rejection of an empty target unit.
    @Test
    void testInvalidTargetUnitValidation() {
        assertThrows(
                QuantityMeasurementException.class,
                () -> controller.convert(
                        new QuantityDTO(1.0, "FEET"),
                        ""
                )
        );
    }

    // Tests rejection of a null service dependency.
    @Test
    void testNullServiceDependency() {
        assertThrows(
                NullPointerException.class,
                () -> new QuantityMeasurementController(null)
        );
    }

    // Provides a fake service to verify controller delegation.
    private static class TestService implements IQuantityMeasurementService {

        private int convertCalls;
        private int compareCalls;
        private int calculateCalls;
        private int getAllCalls;
        private int getByIdCalls;

        @Override
        public QuantityMeasurementEntity convert(
                QuantityDTO source, String targetUnit) {
            convertCalls++;
            return null;
        }

        @Override
        public QuantityMeasurementEntity compare(
                QuantityDTO first, QuantityDTO second) {
            compareCalls++;
            return null;
        }

        @Override
        public QuantityMeasurementEntity calculate(
                QuantityDTO first, QuantityDTO second, String operation) {
            calculateCalls++;
            return null;
        }

        @Override
        public List<QuantityMeasurementEntity> getAllMeasurements() {
            getAllCalls++;
            return new ArrayList<>();
        }

        @Override
        public QuantityMeasurementEntity getMeasurementById(String id) {
            getByIdCalls++;
            return null;
        }
    }
}