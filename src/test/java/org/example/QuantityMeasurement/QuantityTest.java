
/*
 * Class Name  : QuantityTest
 * Description : Tests generic quantity operations across
 *               length and weight measurement categories.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void testIMeasurable_LengthUnitImplementation() {
        IMeasurable unit = LengthUnit.FEET;

        assertEquals(1.0, unit.getConversionFactor());
        assertEquals(2.0, unit.convertToBaseUnit(2.0));
        assertEquals(2.0, unit.convertFromBaseUnit(2.0));
        assertEquals("FEET", unit.getUnitName());
    }

    @Test
    void testIMeasurable_WeightUnitImplementation() {
        IMeasurable unit = WeightUnit.KILOGRAM;

        assertEquals(1.0, unit.getConversionFactor());
        assertEquals(2.0, unit.convertToBaseUnit(2.0));
        assertEquals(2.0, unit.convertFromBaseUnit(2.0));
        assertEquals("KILOGRAM", unit.getUnitName());
    }

    @Test
    void testGenericQuantity_LengthEquality() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
        assertEquals(feet.hashCode(), inches.hashCode());
    }

    @Test
    void testGenericQuantity_WeightEquality() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, grams);
        assertEquals(kg.hashCode(), grams.hashCode());
    }

    @Test
    void testCrossCategoryPrevention() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(feet, kg);
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testLengthAddition() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testWeightAddition() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(grams, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testAddition_DefaultTargetUnit() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.add(inches);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testConstructor_NaNValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testConstructor_InfiniteValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY,
                        WeightUnit.KILOGRAM));
    }

    @Test
    void testConversion_NullTargetUnit() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(NullPointerException.class,
                () -> feet.convertTo(null));
    }

    @Test
    void testAddition_NullQuantity() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(NullPointerException.class,
                () -> feet.add(null));
    }

    @Test
    void testImmutability() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(1.0, feet.getValue());
        assertEquals(12.0, inches.getValue());
        assertNotSame(feet, inches);
    }

    @Test
    void testDemonstrationMethods() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(feet, inches));

        assertEquals(12.0,
                QuantityMeasurementApp.demonstrateConversion(
                        feet, LengthUnit.INCHES).getValue(), 0.000001);

        assertEquals(2.0,
                QuantityMeasurementApp.demonstrateAddition(
                        feet, inches, LengthUnit.FEET).getValue(), 0.000001);
    }

    @Test
    void testToString() {
        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals("Quantity(1.0, FEET)", feet.toString());
    }
}