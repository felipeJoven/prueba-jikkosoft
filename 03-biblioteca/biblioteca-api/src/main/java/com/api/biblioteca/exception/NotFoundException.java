package com.api.biblioteca.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String entity) {
        super(String.format(message, entity));
    }
}
