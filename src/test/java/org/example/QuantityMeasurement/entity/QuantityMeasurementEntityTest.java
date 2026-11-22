
/*
 * Class Name  : QuantityMeasurementEntityTest
 * Description : Tests UC15 measurement entity construction and behaviour.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementEntityTest {

    // Tests creation of a single-operand entity.
    @Test
    void testSingleOperandConstructor() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(1.0, "FEET");

        assertEquals(1.0, entity.getFirstValue());
        assertEquals("FEET", entity.getFirstUnit());
        assertEquals("CONVERSION", entity.getOperation());
        assertTrue(entity.isSuccessful());
    }

    // Tests creation of a successful conversion entity.
    @Test
    void testConversionConstructor() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        1.0, "FEET", "CONVERSION", 12.0, "INCHES"
                );

        assertEquals(12.0, entity.getResult());
        assertEquals("INCHES", entity.getResultUnit());
        assertTrue(entity.isSuccessful());
    }

    // Tests creation of a successful arithmetic entity.
    @Test
    void testArithmeticConstructor() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        1.0, "FEET",
                        12.0, "INCHES",
                        "ADD", 2.0, "FEET"
                );

        assertEquals(1.0, entity.getFirstValue());
        assertEquals(12.0, entity.getSecondValue());
        assertEquals("ADD", entity.getOperation());
        assertEquals(2.0, entity.getResult());
        assertTrue(entity.isSuccessful());
    }

    // Tests creation of an error entity.
    @Test
    void testErrorConstructor() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        1.0, "FEET",
                        2.0, "KILOGRAM",
                        "COMPARE",
                        "Incompatible measurement types"
                );

        assertFalse(entity.isSuccessful());
        assertEquals(
                "Incompatible measurement types",
                entity.getErrorMessage()
        );
    }

    // Tests generated entity ID.
    @Test
    void testEntityId() {
        QuantityMeasurementEntity first =
                new QuantityMeasurementEntity(1.0, "FEET");
        QuantityMeasurementEntity second =
                new QuantityMeasurementEntity(2.0, "FEET");

        assertNotNull(first.getId());
        assertNotNull(second.getId());
        assertNotEquals(first.getId(), second.getId());
    }

    // Tests entity string representation.
    @Test
    void testToString() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(1.0, "FEET");

        assertNotNull(entity.toString());
        assertTrue(entity.toString().contains("FEET"));
    }
}