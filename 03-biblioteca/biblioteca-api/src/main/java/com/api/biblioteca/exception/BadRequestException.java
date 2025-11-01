package com.api.biblioteca.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, String entity) {
        super(String.format(message, entity));
    }
}
