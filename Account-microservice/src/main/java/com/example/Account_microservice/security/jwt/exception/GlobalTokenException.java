package com.example.Account_microservice.security.jwt.exception;


import com.example.Account_microservice.user.exception.Validate;
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

    @ExceptionHandler(BadDataTokenCustomerException.class)
    public ResponseEntity<Validate> handlerBadDataTokenCustomerException(BadDataTokenCustomerException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getValidate());
    }

    @ExceptionHandler(CustomerSignatureException.class)
    public ResponseEntity<Validate> handlerCustomerSignatureException(CustomerSignatureException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getValidate());
    }
}
