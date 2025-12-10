package com.phasezero.catalog_service.exception;

public class ProductNotFoundException extends RuntimeException {

    private final String message;

    public ProductNotFoundException(String message) {
        this.message = message; // store message yourself
    }

    @Override
    public String getMessage() {
        return message; 
    }
}
