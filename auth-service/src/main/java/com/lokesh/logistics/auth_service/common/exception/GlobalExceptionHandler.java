package com.lokesh.logistics.auth_service.common.exception;

import com.lokesh.logistics.auth_service.common.dto.ErrorResponse;
import com.lokesh.logistics.auth_service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.getReasonPhrase()
                , ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePhoneNumberAlreadyExists(
            PhoneAlreadyExistsException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.getReasonPhrase()
                , ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserNameAlreadyExists(
            UsernameAlreadyExistsException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.getReasonPhrase()
                , ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(DisabledUserFoundExpection.class)
    public ResponseEntity<ErrorResponse> handleDisabledUser(
            DisabledUserFoundExpection ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()
                , ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
        InvalidCredentialsException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.getReasonPhrase()
                , ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.getReasonPhrase()
                , ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


}
