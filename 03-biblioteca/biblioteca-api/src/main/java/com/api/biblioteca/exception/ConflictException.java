package com.api.biblioteca.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, String entity) {
        super(String.format(message, entity));
    }
}
