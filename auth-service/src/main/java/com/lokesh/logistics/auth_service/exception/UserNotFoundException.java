package com.lokesh.logistics.auth_service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User '" + message + "' does not exist.");
    }
}
