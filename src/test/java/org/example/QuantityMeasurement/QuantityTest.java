
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


    @Test
    void testVolumeUnitConversionFactors() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor());
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor());
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor());
    }

    @Test
    void testVolumeUnitBaseConversions() {
        assertEquals(5.0, VolumeUnit.LITRE.convertToBaseUnit(5.0), 0.000001);
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), 0.000001);
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), 0.000001);

        assertEquals(2.0, VolumeUnit.LITRE.convertFromBaseUnit(2.0), 0.000001);
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), 0.000001);
        assertEquals(1.0, VolumeUnit.GALLON.convertFromBaseUnit(3.78541), 0.000001);
    }

    @Test
    void testVolumeEquality() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, millilitre);
        assertEquals(millilitre, litre);
    }

    @Test
    void testVolumeConversion() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(
                1000.0,
                litre.convertTo(VolumeUnit.MILLILITRE).getValue(),
                0.000001
        );

        assertEquals(
                3.78541,
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE).getValue(),
                0.000001
        );

        assertEquals(
                1.0,
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.GALLON).getValue(),
                0.000001
        );
    }

    @Test
    void testVolumeAddition() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(2.0, litre.add(millilitre).getValue(), 0.000001);
        assertEquals(2000.0,
                litre.add(millilitre, VolumeUnit.MILLILITRE).getValue(),
                0.000001);
        assertEquals(2.0,
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.GALLON),
                                VolumeUnit.GALLON).getValue(),
                0.000001);
    }

    @Test
    void testVolumeCategoryIsolation() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertNotEquals(litre, new Quantity<>(1.0, LengthUnit.FEET));
        assertNotEquals(litre, new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testVolumeEdgeCases() {
        Quantity<VolumeUnit> zero = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> negative = new Quantity<>(-1.0, VolumeUnit.LITRE);

        assertEquals(0.0,
                zero.convertTo(VolumeUnit.MILLILITRE).getValue(), 0.000001);

        assertEquals(-1000.0,
                negative.convertTo(VolumeUnit.MILLILITRE).getValue(), 0.000001);

        assertEquals(2_000_000.0,
                new Quantity<>(1_000_000.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1_000_000.0, VolumeUnit.LITRE))
                        .getValue(), 0.000001);
    }

    @Test
    void testVolumeRoundTripConversion() {
        Quantity<VolumeUnit> original =
                new Quantity<>(1.5, VolumeUnit.LITRE);

        double convertedBack = original
                .convertTo(VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE)
                .getValue();

        assertEquals(1.5, convertedBack, 0.000001);
    }

    @Test
    void testVolumeAdditionWithZero() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(5.0, result.getValue(), 0.000001);
    }

    @Test
    void testVolumeAdditionWithNegativeValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(3.0, result.getValue(), 0.000001);
    }

    @Test
    void testVolumeImmutability() {
        Quantity<VolumeUnit> original =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> converted =
                original.convertTo(VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> added =
                original.add(new Quantity<>(1.0, VolumeUnit.LITRE));

        assertEquals(1.0, original.getValue());
        assertEquals(1000.0, converted.getValue(), 0.000001);
        assertEquals(2.0, added.getValue(), 0.000001);
    }

    @Test
    void testVolumeHashCodeConsistency() {
        Quantity<VolumeUnit> first =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> second =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testVolumeGenericDemonstrationMethods() {
        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitre =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(litre, millilitre));

        assertEquals(1000.0,
                QuantityMeasurementApp.demonstrateConversion(
                        litre, VolumeUnit.MILLILITRE).getValue(),
                0.000001);

        assertEquals(2.0,
                QuantityMeasurementApp.demonstrateAddition(
                        litre, millilitre, VolumeUnit.LITRE).getValue(),
                0.000001);
    }
    @Test
    void testSubtractionSameUnit() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(4.0, LengthUnit.FEET));

        assertEquals(6.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testSubtractionDifferentUnits() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(0.5, result.getValue(), 0.000001);
    }

    @Test
    void testSubtractionWithExplicitTargetUnit() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .subtract(
                                new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.INCHES
                        );

        assertEquals(6.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testSubtractionNegativeResult() {
        Quantity<WeightUnit> result =
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM));

        assertEquals(-3.0, result.getValue(), 0.000001);
    }

    @Test
    void testSubtractionZeroResult() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(0.0, result.getValue(), 0.000001);
    }

    @Test
    void testSubtractionPreservesSmallPrecision() {
        Quantity<VolumeUnit> result =
                new Quantity<>(0.001, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(0.0005, VolumeUnit.LITRE));

        assertEquals(0.0005, result.getValue(), 0.000000001);
    }

    @Test
    void testSubtractionNullOther() {
        Quantity<LengthUnit> quantity =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> quantity.subtract(null)
        );
    }

    @Test
    void testSubtractionNullTargetUnit() {
        Quantity<LengthUnit> quantity =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> quantity.subtract(
                        new Quantity<>(2.0, LengthUnit.FEET),
                        null
                )
        );
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testSubtractionCrossCategory() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.subtract(weight)
        );
    }

    @Test
    void testSubtractionDoesNotModifyOriginalQuantities() {
        Quantity<LengthUnit> first =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> second =
                new Quantity<>(4.0, LengthUnit.FEET);

        first.subtract(second);

        assertEquals(10.0, first.getValue());
        assertEquals(4.0, second.getValue());
    }
    @Test
    void testDivisionSameUnit() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, 0.000001);
    }

    @Test
    void testDivisionDifferentUnits() {
        double result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .divide(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(2.0, result, 0.000001);
    }

    @Test
    void testDivisionRatioOne() {
        double result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1000.0, WeightUnit.GRAM));

        assertEquals(1.0, result, 0.000001);
    }

    @Test
    void testDivisionRatioLessThanOne() {
        double result =
                new Quantity<>(2.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(4.0, VolumeUnit.LITRE));

        assertEquals(0.5, result, 0.000001);
    }

    @Test
    void testDivisionNegativeValues() {
        double result =
                new Quantity<>(-10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(-5.0, result, 0.000001);
    }

    @Test
    void testDivisionByZero() {
        Quantity<LengthUnit> quantity =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> quantity.divide(new Quantity<>(0.0, LengthUnit.INCHES))
        );
    }

    @Test
    void testDivisionNullOther() {
        Quantity<WeightUnit> quantity =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> quantity.divide(null)
        );
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testDivisionCrossCategory() {
        Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide(weight)
        );
    }

    @Test
    void testDivisionDoesNotModifyOriginalQuantities() {
        Quantity<VolumeUnit> first =
                new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> second =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        first.divide(second);

        assertEquals(10.0, first.getValue());
        assertEquals(2.0, second.getValue());
    }
}