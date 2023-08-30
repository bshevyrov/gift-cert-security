package com.epam.esm.exception.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception class.
 * Objects of this class can be thrown during authentication, if username not found.
 */
public class InvalidUsernameException extends AuthenticationException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
