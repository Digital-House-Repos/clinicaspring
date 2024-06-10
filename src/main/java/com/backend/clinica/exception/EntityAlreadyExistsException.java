package com.backend.clinica.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String object, Long id) {
        super(object + " con ID " + id + " ya existe.");
    }
}
