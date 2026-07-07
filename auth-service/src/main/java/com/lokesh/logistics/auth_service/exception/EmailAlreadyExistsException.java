package com.lokesh.logistics.auth_service.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super("Email '" + message + "' already exists.");
    }
}
