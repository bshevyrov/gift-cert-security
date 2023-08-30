package com.epam.esm.exception.customer;

/**
 * Exception class.
 * Objects of this class can be thrown during authentication operation, if user disabled.
 */
public class CustomerDisabledException extends RuntimeException {
    public CustomerDisabledException(String message) {
        super(message);
    }
}

