package com.example.Account_microservice.exeption.jwt;


import com.example.Account_microservice.config.ConstantResponseText;
import com.example.Account_microservice.exeption.Validate;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionJwtHandler {


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Validate> handlerExpiredJwtException(){

        return ResponseEntity.badRequest().body(new Validate(ConstantResponseText.VALIDATE_BAD_REQUEST));
    }
}
