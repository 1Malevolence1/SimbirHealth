package com.example.Account_microservice.security.jwt.exception;

import com.example.Account_microservice.exception.Validate;
import lombok.Getter;

@Getter
public class BadDataTokenCustomerException extends RuntimeException{
    private final Validate validate;



    public BadDataTokenCustomerException(Validate validate) {
        this.validate = validate;
    }

    public BadDataTokenCustomerException(String message, Validate validate) {
        super(message);
        this.validate = validate;
    }

    public BadDataTokenCustomerException(String message, Throwable cause, Validate validate) {
        super(message, cause);
        this.validate = validate;
    }

    public BadDataTokenCustomerException(Throwable cause, Validate validate) {
        super(cause);
        this.validate = validate;
    }

    public BadDataTokenCustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate validate) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.validate = validate;
    }
}
