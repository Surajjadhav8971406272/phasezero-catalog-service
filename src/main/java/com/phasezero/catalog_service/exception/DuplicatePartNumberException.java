package com.phasezero.catalog_service.exception;

public class DuplicatePartNumberException extends RuntimeException {

    private final String message;

    public DuplicatePartNumberException(String message) {
        this.message = message; // your custom message
    }

    @Override
    public String getMessage() {
        return message;
    }
}
