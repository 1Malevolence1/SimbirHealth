package com.example.Account_microservice.user.service.user;

import com.example.Account_microservice.exception.Validate;
import lombok.Getter;

@Getter
public class UsernameAlreadyExistsException extends RuntimeException {
    private final Validate validate;

    public UsernameAlreadyExistsException(Validate validate) {
        this.validate = validate;
    }

    public UsernameAlreadyExistsException(String message, Validate validate) {
        super(message);
        this.validate = validate;
    }

    public UsernameAlreadyExistsException(String message, Throwable cause, Validate validate) {
        super(message, cause);
        this.validate = validate;
    }

    public UsernameAlreadyExistsException(Throwable cause, Validate validate) {
        super(cause);
        this.validate = validate;
    }

    public UsernameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate validate) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.validate = validate;
    }
}
