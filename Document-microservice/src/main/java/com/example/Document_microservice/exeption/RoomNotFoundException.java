package com.example.Document_microservice.exeption;

import lombok.Getter;

@Getter
public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Validate error) {
        this.error = error;
    }

    public RoomNotFoundException(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public RoomNotFoundException(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public RoomNotFoundException(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public RoomNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    private final Validate error;
}
