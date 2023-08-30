package com.epam.esm.exception.auth;

/**
 * Exception class.
 * Objects of this class can be thrown during authentication, password for corresponding username is incorrect.
 */
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
