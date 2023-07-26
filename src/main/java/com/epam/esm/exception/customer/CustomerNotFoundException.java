package com.epam.esm.exception.customer;

/**
 * Exception class.
 * Objects of this class can be thrown during find by id operation, if record not present in DB.
 */
public class CustomerNotFoundException extends RuntimeException {

    private final long customerId;

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public long getCustomerId() {
        return customerId;
    }
}
