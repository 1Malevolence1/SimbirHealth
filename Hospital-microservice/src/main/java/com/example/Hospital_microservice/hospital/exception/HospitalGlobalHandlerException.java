package com.example.Hospital_microservice.hospital.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class HospitalGlobalHandlerException {


    @ExceptionHandler(BindException.class)
    public ResponseEntity<BindExceptionListCustomer> handlerBindException(BindException exception){
        List<Validate> errors = exception.getAllErrors().stream().map(
                error -> new Validate(error.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(new BindExceptionListCustomer(errors));
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Validate> handlerNoSuchElementException(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Validate(exception.getMessage()));
    }

}
