package com.example.Timetable_microservice.timetable.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final Validate validate;

    public UnauthorizedException(Validate validate) {
        this.validate = validate;
    }

    public UnauthorizedException(String message, Validate validate) {
        super(message);
        this.validate = validate;
    }

    public UnauthorizedException(String message, Throwable cause, Validate validate) {
        super(message, cause);
        this.validate = validate;
    }

    public UnauthorizedException(Throwable cause, Validate validate) {
        super(cause);
        this.validate = validate;
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate validate) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.validate = validate;
    }
}
