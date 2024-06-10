package com.backend.clinica.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String object, String type, Object value) {
        super(object + " ya existe con " + type + ": " + value);
    }
}
