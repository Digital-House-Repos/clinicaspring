package com.backend.clinica.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String object, String type, Object value) {
        super(object + " no encontrado con " + type + ": " + value);
    }
}
