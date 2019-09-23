package com.seatcode.robotread;

/**
 * {@code RuntimeException} that is thrown when an error occurs during a request. An
 * example would be, transformer the record to a json object.
 */
public class RobotException extends RuntimeException {

    private static final long serialVersionUID = 4151292570011428944L;

    public RobotException(String message) {
        super(message);
    }
}
