package com.example.Account_microservice.security.exception.jwt;


import com.example.Account_microservice.config.ConstantResponseText;
import com.example.Account_microservice.exception.Validate;
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
