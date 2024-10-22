package com.example.Timetable_microservice.appointment.exception;

import com.example.Timetable_microservice.timetable.exception.Validate;
import lombok.Getter;

@Getter
public class RecordUserExceptionCustomer extends RuntimeException {
    private final Validate validate;

    public RecordUserExceptionCustomer(Validate validate) {
        this.validate = validate;
    }

    public RecordUserExceptionCustomer(String message, Validate validate) {
        super(message);
        this.validate = validate;
    }

    public RecordUserExceptionCustomer(String message, Throwable cause, Validate validate) {
        super(message, cause);
        this.validate = validate;
    }

    public RecordUserExceptionCustomer(Throwable cause, Validate validate) {
        super(cause);
        this.validate = validate;
    }

    public RecordUserExceptionCustomer(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate validate) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.validate = validate;
    }
}
