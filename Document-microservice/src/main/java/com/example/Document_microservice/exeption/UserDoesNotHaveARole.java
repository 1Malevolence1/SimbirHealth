package com.example.Document_microservice.exeption;


import lombok.Getter;

@Getter
public class UserDoesNotHaveARole extends RuntimeException {
    public UserDoesNotHaveARole(Validate error) {
        this.error = error;
    }

    public UserDoesNotHaveARole(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public UserDoesNotHaveARole(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public UserDoesNotHaveARole(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public UserDoesNotHaveARole(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    private final Validate error;
}
