package com.example.Account_microservice.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalUsernameException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Validate> handlerUsernameNotFoundException(UsernameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Validate(exception.getMessage()));
    }
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Validate> handlerUsernameAlreadyExistsException(UsernameAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getValidate());
    }

}
