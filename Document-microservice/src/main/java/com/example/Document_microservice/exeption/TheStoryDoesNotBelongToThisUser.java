package com.example.Document_microservice.exeption;

import lombok.Getter;

@Getter
public class TheStoryDoesNotBelongToThisUser extends RuntimeException  {
    private final Validate error;

    public TheStoryDoesNotBelongToThisUser(Validate error) {
        this.error = error;
    }

    public TheStoryDoesNotBelongToThisUser(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public TheStoryDoesNotBelongToThisUser(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public TheStoryDoesNotBelongToThisUser(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public TheStoryDoesNotBelongToThisUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
