package com.epam.esm.exception;

import com.epam.esm.exception.auth.InvalidPasswordException;
import com.epam.esm.exception.auth.InvalidTokenException;
import com.epam.esm.exception.auth.InvalidUsernameException;
import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.exception.giftcertificate.GiftCertificateUpdateException;
import com.epam.esm.exception.tag.TagExistException;
import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.veiw.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class catch exceptions which might occur during code execution.
 * Contains handlers to produce response if exception occur.
 */
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(GiftCertificateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleGiftCertificateNotFoundException(GiftCertificateNotFoundException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.NOT_FOUND.value() + "04"), e.getMessage());
    }

    @ExceptionHandler(GiftCertificateUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleGiftCertificateUpdateException(GiftCertificateUpdateException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.BAD_REQUEST.value() + "06"), e.getMessage());
    }


    @ExceptionHandler(TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTagNotFoundException(TagNotFoundException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.NOT_FOUND.value() + "04"), e.getMessage());
    }


    @ExceptionHandler(TagExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTagExistException(TagExistException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.BAD_REQUEST.value() + "07"), e.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidTokenException(InvalidTokenException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.UNAUTHORIZED.value() + "00"), e.getMessage());
    }

    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidTokenException(InvalidUsernameException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.UNAUTHORIZED.value() + "01"), e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidTokenException(InvalidPasswordException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.UNAUTHORIZED.value() + "02"), e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.INTERNAL_SERVER_ERROR.value() + "00"), e.getMessage() + "" + Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        return new ErrorResponse(Integer.parseInt(HttpStatus.INTERNAL_SERVER_ERROR.value() + "00"), e.getConstraintViolations().iterator().next().getPropertyPath().toString());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}