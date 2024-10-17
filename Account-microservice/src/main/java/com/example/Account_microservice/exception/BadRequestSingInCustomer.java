package com.example.Account_microservice.exception;


import lombok.Getter;


@Getter
public class BadRequestSingInCustomer extends RuntimeException {

    private final Validate error;

    public BadRequestSingInCustomer(Validate error) {
        this.error = error;
    }

    public BadRequestSingInCustomer(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public BadRequestSingInCustomer(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public BadRequestSingInCustomer(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public BadRequestSingInCustomer(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
