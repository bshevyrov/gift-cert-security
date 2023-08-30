package com.epam.esm.exception.auth;


import org.springframework.security.core.AuthenticationException;

/**
 * Exception class.
 * Objects of this class can be thrown during token validation  operation, if token is invalid or expire.
 */
public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
