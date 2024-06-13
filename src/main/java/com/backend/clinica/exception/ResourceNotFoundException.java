package com.backend.clinica.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String object, String type, Object value) {
        super(object + " no encontrado con " + type + ": " + value);
    }
}
