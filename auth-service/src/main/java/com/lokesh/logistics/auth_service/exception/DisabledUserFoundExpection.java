package com.lokesh.logistics.auth_service.exception;

public class DisabledUserFoundExpection extends RuntimeException {
    public DisabledUserFoundExpection(String message) {
        super(message);
    }
}
