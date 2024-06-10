package com.backend.clinica.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String object) {
        super(object + " no encontrado.");
    }
}
