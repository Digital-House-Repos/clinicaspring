package com.backend.clinica.controller;

import org.springframework.http.ResponseEntity;

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

    public ResponseEntity<CustomResponse> getResponse(Integer code) {
        return ResponseEntity.status(code).body(this);
    }
}
