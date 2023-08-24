package com.epam.esm.exception.order;

public class PopularOrderNotFoundException extends RuntimeException {
    public PopularOrderNotFoundException(String message) {
        super(message);
    }
}
