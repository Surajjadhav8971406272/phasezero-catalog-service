package com.phasezero.catalog_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicatePartNumberException.class)
    public ResponseEntity<String> handleDuplicate(DuplicatePartNumberException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {

        if (ex instanceof org.springframework.web.bind.MissingServletRequestParameterException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Required request parameter is missing");
        }


        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid endpoint or URL not found");
        }
        if (ex instanceof org.springframework.web.bind.MethodArgumentNotValidException) {
            String msg = ((org.springframework.web.bind.MethodArgumentNotValidException) ex)
                    .getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong: " + ex.getMessage());
    }
}