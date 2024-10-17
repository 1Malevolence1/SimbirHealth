package com.example.Document_microservice.document.exeption;

import lombok.Getter;

@Getter
public class TheUserHasSeveralRoles extends RuntimeException {
    private final Validate error;

    public TheUserHasSeveralRoles(Validate error) {
        this.error = error;
    }

    public TheUserHasSeveralRoles(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public TheUserHasSeveralRoles(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public TheUserHasSeveralRoles(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public TheUserHasSeveralRoles(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
