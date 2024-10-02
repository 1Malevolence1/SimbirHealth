package com.example.Account_microservice.user.exception;


import com.example.Account_microservice.exception.BadRequestExceptionCustomer;
import com.example.Account_microservice.exception.BadRequestSingInCustomer;
import com.example.Account_microservice.exception.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionForUser {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Validate> handlerNoSuchElementException(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Validate(exception.getMessage()));
    }

    @ExceptionHandler(BadRequestSingInCustomer.class)
    public ResponseEntity<Validate> handlerBadRequestSingInCustomer(BadRequestSingInCustomer badRequestSingInCustomer){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(badRequestSingInCustomer.getError());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BadRequestExceptionCustomer> handlerBindExcept(BindException exception) {
        List<Validate> errors = exception.getAllErrors().stream().map(
                error -> new Validate(error.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(new BadRequestExceptionCustomer(errors));
    }



    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Validate> handlerMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResponseEntity.badRequest().body(new Validate(exception.getMessage()));
    }


}
