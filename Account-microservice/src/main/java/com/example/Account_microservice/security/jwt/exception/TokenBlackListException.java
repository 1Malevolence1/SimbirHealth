package com.example.Account_microservice.security.jwt.exception;


import lombok.Getter;

@Getter
public class TokenBlackListException  extends RuntimeException{
    private final ValidateToken validateToken;

    public TokenBlackListException(ValidateToken validateToken) {
        this.validateToken = validateToken;
    }

    public TokenBlackListException(String message, ValidateToken validateToken) {
        super(message);
        this.validateToken = validateToken;
    }

    public TokenBlackListException(String message, Throwable cause, ValidateToken validateToken) {
        super(message, cause);
        this.validateToken = validateToken;
    }

    public TokenBlackListException(Throwable cause, ValidateToken validateToken) {
        super(cause);
        this.validateToken = validateToken;
    }

    public TokenBlackListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ValidateToken validateToken) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.validateToken = validateToken;
    }
}
