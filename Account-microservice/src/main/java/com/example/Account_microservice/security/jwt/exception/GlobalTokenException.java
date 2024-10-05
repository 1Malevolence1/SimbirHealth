package com.example.Account_microservice.security.jwt.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalTokenException {

    @ExceptionHandler(TokenBlackListException.class)
    public ResponseEntity<ValidateToken> handlerTokenBlackListException(TokenBlackListException tokenBlackListException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(tokenBlackListException.getValidateToken());
    }
}
