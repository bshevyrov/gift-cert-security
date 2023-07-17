package com.epam.esm.exception.customer;

/**
 * Exception class.
 * Objects of this class can be thrown during id validation, if id is illegal.
 */
public class CustomerIdException extends RuntimeException {
    private final long customerId;

    public CustomerIdException(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }
}