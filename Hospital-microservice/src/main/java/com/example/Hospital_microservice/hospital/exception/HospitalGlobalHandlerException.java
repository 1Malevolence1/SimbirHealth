package com.example.Hospital_microservice.hospital.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;

@RestControllerAdvice
public class HospitalGlobalHandlerException {


    @ExceptionHandler(BindException.class)
    public ResponseEntity<BindExceptionListCustomer> handlerBindException(BindException exception){
        List<Validate> errors = exception.getAllErrors().stream().map(
                error -> new Validate(error.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(new BindExceptionListCustomer(errors));
    }

}
