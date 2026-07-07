package com.lokesh.logistics.auth_service.exception;

public class PhoneAlreadyExistsException extends RuntimeException {
    public PhoneAlreadyExistsException(String message) {
        super("Phone number  '" + message + "' already exists.");
    }
}
