
/*
 * Class Name  : QuantityMeasurementAppTest
 * Description : Unit tests for UC1 and UC2.
 * Author      : Manthan Hanchate
 * Date        : 17/11/2026
 */

package org.example.QuantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // UC1: Feet equality

    @Test
    void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    // UC2: Inches equality

    @Test
    void testInchesEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    // UC2: Feet-to-Inches equality

    @Test
    void testFeetInchesEquality_EquivalentValues() {
        assertTrue(QuantityMeasurementApp.areFeetAndInchesEqual(1.0, 12.0));
    }

    @Test
    void testFeetInchesEquality_DifferentValues() {
        assertFalse(QuantityMeasurementApp.areFeetAndInchesEqual(1.0, 10.0));
    }

    @Test
    void testFeetInchesEquality_ZeroValues() {
        assertTrue(QuantityMeasurementApp.areFeetAndInchesEqual(0.0, 0.0));
    }

    @Test
    void testFeetInchesEquality_DecimalValues() {
        assertTrue(QuantityMeasurementApp.areFeetAndInchesEqual(0.5, 6.0));
    }

    // Object equality edge cases

    @Test
    void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(null, feet);
    }

    @Test
    void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertNotEquals(null, inches);
    }

    @Test
    void testFeetEquality_DifferentObjectType() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(feet, "1.0");
    }

    @Test
    void testInchesEquality_DifferentObjectType() {
        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertNotEquals(inches, "1.0");
    }

    @Test
    void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet, feet);
    }

    @Test
    void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertEquals(inches, inches);
    }

    @Test
    void testFeetEquality_HashCodeConsistency() {
        QuantityMeasurementApp.Feet feet1 =
                new QuantityMeasurementApp.Feet(1.0);

        QuantityMeasurementApp.Feet feet2 =
                new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet1.hashCode(), feet2.hashCode());
    }

    @Test
    void testInchesEquality_HashCodeConsistency() {
        QuantityMeasurementApp.Inches inches1 =
                new QuantityMeasurementApp.Inches(1.0);

        QuantityMeasurementApp.Inches inches2 =
                new QuantityMeasurementApp.Inches(1.0);

        assertEquals(inches1.hashCode(), inches2.hashCode());
    }
}