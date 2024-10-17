package com.example.Account_microservice.exception;

import lombok.Getter;

@Getter
public class BadRequestRolesException extends RuntimeException {
    private final Validate error;

    public BadRequestRolesException(Validate error) {
        this.error = error;
    }

    public BadRequestRolesException(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public BadRequestRolesException(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public BadRequestRolesException(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public BadRequestRolesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
