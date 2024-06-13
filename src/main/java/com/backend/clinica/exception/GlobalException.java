package com.backend.clinica.exception;

import com.backend.clinica.controller.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponse> handlerEntityNotFoun(ResourceNotFoundException e) {
        CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
        return ResponseEntity.status(404).body(cr);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomResponse> handlerBadRequest(BadRequestException e) {
        CustomResponse cr = new CustomResponse(false, e.getMessage(), "Bad request");
        return ResponseEntity.status(400).body(cr);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<CustomResponse> handlerEntityAlreadyExists(EntityAlreadyExistsException e) {
        CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity already exists");
        return ResponseEntity.status(400).body(cr);
    }
}
