
/*
 * Class Name  : QuantityMeasurementApp
 * Description : Application entry point coordinating measurement operations.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement;

import org.example.QuantityMeasurement.controller.QuantityMeasurementController;
import org.example.QuantityMeasurement.dto.QuantityDTO;
import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;
import org.example.QuantityMeasurement.repository.IQuantityMeasurementRepository;
import org.example.QuantityMeasurement.repository.QuantityMeasurementCacheRepository;
import org.example.QuantityMeasurement.service.IQuantityMeasurementService;
import org.example.QuantityMeasurement.service.QuantityMeasurementServiceImpl;

import java.util.List;
import java.util.Objects;

public class QuantityMeasurementApp {

    private final QuantityMeasurementController controller;

    // Initializes repository, service, and controller.
    public QuantityMeasurementApp() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        this.controller = new QuantityMeasurementController(service);
    }

    // Allows controller injection for testing.
    public QuantityMeasurementApp(QuantityMeasurementController controller) {
        this.controller = Objects.requireNonNull(
                controller, "Controller cannot be null");
    }

    // UC15: Delegates conversion requests to the controller.
    public QuantityMeasurementEntity convert(
            QuantityDTO source, String targetUnit) {
        return controller.convert(source, targetUnit);
    }

    // UC15: Delegates comparison requests to the controller.
    public QuantityMeasurementEntity compare(
            QuantityDTO first, QuantityDTO second) {
        return controller.compare(first, second);
    }

    // UC15: Delegates arithmetic requests to the controller.
    public QuantityMeasurementEntity calculate(
            QuantityDTO first, QuantityDTO second, String operation) {
        return controller.calculate(first, second, operation);
    }

    // UC15: Retrieves all stored measurement records.
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return controller.getAllMeasurements();
    }

    // UC15: Retrieves a measurement record by ID.
    public QuantityMeasurementEntity getMeasurementById(String id) {
        return controller.getMeasurementById(id);
    }

    // UC10: Generic equality demonstration.
    public static boolean demonstrateEquality(
            Quantity<?> quantity1, Quantity<?> quantity2) {
        return quantity1.equals(quantity2);
    }

    // UC10: Generic conversion demonstration.
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    // UC10: Generic addition demonstration.
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        return quantity1.add(quantity2, targetUnit);
    }

    // UC1 and UC2: Legacy equality methods.
    public static boolean areFeetEqual(double value1, double value2) {
        return Double.compare(value1, value2) == 0;
    }

    public static boolean areInchesEqual(double value1, double value2) {
        return Double.compare(value1, value2) == 0;
    }

    public static boolean areFeetAndInchesEqual(
            double feetValue, double inchesValue) {

        return Double.compare(
                LengthUnit.FEET.convertToBaseUnit(feetValue),
                LengthUnit.INCHES.convertToBaseUnit(inchesValue)
        ) == 0;
    }

    // UC5: Legacy length conversion and comparison methods.
    public static double demonstrateLengthConversion(
            double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        return QuantityLength.convert(value, sourceUnit, targetUnit);
    }

    public static QuantityLength demonstrateLengthConversion(
            QuantityLength quantity, LengthUnit targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {

        return new QuantityLength(value1, unit1)
                .equals(new QuantityLength(value2, unit2));
    }

    // UC1: Legacy Feet class.
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet feet = (Feet) obj;
            return Double.compare(value, feet.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    // UC2: Legacy Inches class.
    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches inches = (Inches) obj;
            return Double.compare(value, inches.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    // UC10: Generic subtraction demonstration.
    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.subtract(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        return quantity1.subtract(quantity2, targetUnit);
    }

    // UC12: Generic division demonstration.
    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.divide(quantity2);
    }

    // Application entry point.
    public static void main(String[] args) {

        QuantityMeasurementApp app = new QuantityMeasurementApp();

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<WeightUnit> kilogram =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Length Operations:");
        System.out.println("Equality: " + demonstrateEquality(feet, inches));
        System.out.println("Conversion: "
                + demonstrateConversion(feet, LengthUnit.INCHES));
        System.out.println("Addition: "
                + demonstrateAddition(feet, inches, LengthUnit.FEET));

        System.out.println("\nWeight Operations:");
        System.out.println("Equality: "
                + demonstrateEquality(kilogram, grams));
        System.out.println("Conversion: "
                + demonstrateConversion(kilogram, WeightUnit.GRAM));
        System.out.println("Addition: "
                + demonstrateAddition(kilogram, grams, WeightUnit.KILOGRAM));

        // UC15: Demonstrates controller-service-repository flow.
        System.out.println("\nUC15 Application Layer:");

        QuantityMeasurementEntity conversion =
                app.convert(new QuantityDTO(1.0, "FEET"), "INCHES");

        System.out.println("Conversion Result: " + conversion);

        System.out.println("Stored Records: "
                + app.getAllMeasurements().size());
    }
}