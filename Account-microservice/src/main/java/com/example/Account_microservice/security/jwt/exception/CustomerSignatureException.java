package com.example.Account_microservice.security.jwt.exception;

import com.example.Account_microservice.user.exception.Validate;
import lombok.Getter;

@Getter
public class CustomerSignatureException extends RuntimeException {
    private final Validate validate;

    public CustomerSignatureException(Validate error) {

        this.validate = error;
    }

    public CustomerSignatureException(String message, Validate error) {
        super(message);
        this.validate = error;
    }

    public CustomerSignatureException(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.validate = error;
    }

    public CustomerSignatureException(Throwable cause, Validate error) {
        super(cause);
        this.validate = error;
    }

    public CustomerSignatureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.validate = error;
    }
}
