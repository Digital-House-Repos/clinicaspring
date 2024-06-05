package com.backend.clinica.controller;

public class CustomResponse {
    private final Boolean ok;
    private final String message;
    private final Object data;

    public CustomResponse(Boolean ok, String message, Object data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public Boolean getOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
