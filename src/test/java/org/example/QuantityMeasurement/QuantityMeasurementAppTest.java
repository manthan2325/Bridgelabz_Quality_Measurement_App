
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

    // UC4: Yard equality

    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(2.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(36.0, LengthUnit.INCHES));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertEquals(
                new QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(2.0, LengthUnit.FEET));
    }

    // UC4: Centimeter equality

    @Test
    void testEquality_CentimetersToCentimeters_SameValue() {
        assertEquals(
                new QuantityLength(2.0, LengthUnit.CENTIMETERS),
                new QuantityLength(2.0, LengthUnit.CENTIMETERS));
    }

    @Test
    void testEquality_CentimetersToCentimeters_DifferentValue() {
        assertNotEquals(
                new QuantityLength(2.0, LengthUnit.CENTIMETERS),
                new QuantityLength(3.0, LengthUnit.CENTIMETERS));
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(0.393701, LengthUnit.INCHES));
    }

    @Test
    void testEquality_InchesToCentimeters_EquivalentValue() {
        assertEquals(
                new QuantityLength(0.393701, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.CENTIMETERS));
    }

    @Test
    void testEquality_CentimetersToFeet_NonEquivalentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_CentimetersToYards_NonEquivalentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.YARDS));
    }

    // UC4: Multi-unit equality

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    @Test
    void testEquality_YardSameReference() {
        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(yard, yard);
    }

    @Test
    void testEquality_YardNullComparison() {
        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertNotEquals(yard, null);
    }

    @Test
    void testEquality_CentimetersSameReference() {
        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertEquals(cm, cm);
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertNotEquals(cm, null);
    }

    @Test
    void testEquality_YardHashCodeConsistency() {
        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        assertEquals(yard.hashCode(), feet.hashCode());
    }

    @Test
    void testEquality_CentimetersHashCodeConsistency() {
        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inches =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        assertEquals(cm.hashCode(), inches.hashCode());
    }

    // UC5: Basic unit conversions

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET),
                1e-6);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS),
                1e-6);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testConversion_FeetToYards() {
        assertEquals(2.0,
                QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS),
                1e-6);
    }

    // UC5: Same-unit conversion

    @Test
    void testConversion_SameUnit() {
        assertEquals(5.0,
                QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                1e-6);
    }

    // UC5: Zero and negative values

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                1e-6);
    }

    // UC5: Round-trip conversion

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 10.0;

        double converted = QuantityLength.convert(
                original, LengthUnit.FEET, LengthUnit.INCHES);

        double result = QuantityLength.convert(
                converted, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(original, result, 1e-6);
    }

    // UC5: Instance conversion

    @Test
    void testConversion_InstanceMethod() {
        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches = feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, inches.getValue(), 1e-6);
        assertEquals(LengthUnit.INCHES, inches.getUnit());
    }

    @Test
    void testConversion_InstanceDoesNotModifyOriginal() {
        QuantityLength original =
                new QuantityLength(1.0, LengthUnit.FEET);

        original.convertTo(LengthUnit.INCHES);

        assertEquals(1.0, original.getValue(), 1e-6);
        assertEquals(LengthUnit.FEET, original.getUnit());
    }

    // UC5: Invalid inputs

    @Test
    void testConversion_NaNValue() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(
                        Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    @Test
    void testConversion_InfiniteValue() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    void testConversion_NullSourceUnit() {
        assertThrows(NullPointerException.class, () ->
                QuantityLength.convert(
                        1.0, null, LengthUnit.INCHES));
    }

    @Test
    void testConversion_NullTargetUnit() {
        assertThrows(NullPointerException.class, () ->
                QuantityLength.convert(
                        1.0, LengthUnit.FEET, null));
    }

    @Test
    void testConversion_Overflow() {
        assertThrows(ArithmeticException.class, () ->
                QuantityLength.convert(
                        Double.MAX_VALUE,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    // UC5: Method overloading

    @Test
    void testConversion_DemonstrateMethodWithRawValues() {
        assertEquals(12.0,
                QuantityMeasurementApp.demonstrateLengthConversion(
                        1.0, LengthUnit.FEET, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testConversion_DemonstrateMethodWithObject() {
        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityMeasurementApp.demonstrateLengthConversion(
                        feet, LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_DemonstrateLengthComparison() {
        assertTrue(
                QuantityMeasurementApp.demonstrateLengthComparison(
                        1.0, LengthUnit.YARDS,
                        3.0, LengthUnit.FEET));
    }

}