package com.example.Document_microservice.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HistoryGlobalException {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<Validate>> handlerBindException(BindException bindException) {
        List<Validate> validates = bindException.getFieldErrors().stream().map(
                error -> new Validate(
                        error.getDefaultMessage().formatted(
                                error.getField()))
        ).toList();

        return ResponseEntity.badRequest().body(validates);
    }
}
