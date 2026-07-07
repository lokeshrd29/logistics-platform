package com.lokesh.logistics.auth_service.common.exception;

import com.lokesh.logistics.auth_service.common.dto.ErrorResponse;
import com.lokesh.logistics.auth_service.exception.EmailAlreadyExistsException;
import com.lokesh.logistics.auth_service.exception.PhoneAlreadyExistsException;
import com.lokesh.logistics.auth_service.exception.UsernameAlreadyExistsException;
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
}
