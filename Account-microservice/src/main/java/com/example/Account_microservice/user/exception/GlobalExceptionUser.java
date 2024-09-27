package com.example.Account_microservice.user.exception;


import com.example.Account_microservice.exception.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionUser {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Validate> handlerNoSuchElementException(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Validate(exception.getMessage()));
    }
}
