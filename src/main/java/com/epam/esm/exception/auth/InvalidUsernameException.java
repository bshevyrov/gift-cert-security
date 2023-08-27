package com.epam.esm.exception.auth;

/**
 * Exception class.
 * Objects of this class can be thrown during authentication operation, if username not found.
 */
public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

