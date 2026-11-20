
/*
 * Class Name  : QuantityMeasurementAppTest
 * Description : Unit tests for UC1, UC2 and UC3.
 * Author      : Manthan Hanchate
 * Date        : 18/11/2026
 */

package org.example.QuantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // UC1 and UC2 backward compatibility

    @Test
    void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    @Test
    void testInchesEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    @Test
    void testFeetInchesEquality_EquivalentValues() {
        assertTrue(QuantityMeasurementApp.areFeetAndInchesEqual(1.0, 12.0));
    }

    @Test
    void testFeetInchesEquality_DifferentValues() {
        assertFalse(QuantityMeasurementApp.areFeetAndInchesEqual(1.0, 10.0));
    }

    @Test
    void testFeetEquality_NullComparison() {
        assertNotEquals(
                new QuantityMeasurementApp.Feet(1.0), null);
    }

    @Test
    void testInchesEquality_NullComparison() {
        assertNotEquals(
                new QuantityMeasurementApp.Inches(1.0), null);
    }

    // UC3: Same-unit equality

    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertNotEquals(q1, q2);
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.INCHES);

        assertNotEquals(q1, q2);
    }

    // UC3: Cross-unit equality

    @Test
    void testEquality_FeetToInches_EquivalentValue() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    @Test
    void testEquality_InchesToFeet_EquivalentValue() {
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(inches, feet);
    }

    @Test
    void testEquality_FeetToInches_DifferentValue() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(10.0, LengthUnit.INCHES);

        assertNotEquals(feet, inches);
    }

    @Test
    void testEquality_DecimalConversion() {
        QuantityLength feet = new QuantityLength(0.5, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(6.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    // UC3: Validation
    @Test
    void testEquality_InvalidUnit() {
        assertThrows(NullPointerException.class, () ->
                new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(NullPointerException.class, () ->
                new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_NaNValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testEquality_InfiniteValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // UC3: Equality contract

    @Test
    void testEquality_SameReference() {
        QuantityLength quantity =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(quantity, quantity);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength quantity =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(quantity, null);
    }

    @Test
    void testEquality_DifferentObjectType() {
        QuantityLength quantity =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(quantity, "1.0");
    }

    @Test
    void testEquality_HashCodeConsistency() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testEquality_Symmetry() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
        assertEquals(inches, feet);
    }
}