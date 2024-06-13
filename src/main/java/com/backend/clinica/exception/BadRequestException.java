package com.backend.clinica.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String object, String type, String value, String database) {
        super(object + " no existe con " + type + ": " + value + " en la base de datos: " + database);
    }
}
