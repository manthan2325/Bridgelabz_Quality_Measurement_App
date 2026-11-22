
/*
 * Class Name  : QuantityMeasurementException
 * Description : Represents application-specific measurement errors.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.exception;

public class QuantityMeasurementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Creates an exception with a descriptive message.
    public QuantityMeasurementException(String message) {
        super(message);
    }

    // Creates an exception with a message and original cause.
    public QuantityMeasurementException(String message, Throwable cause) {
        super(message, cause);
    }
}