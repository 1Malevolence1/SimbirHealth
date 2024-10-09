package com.example.Timetable_microservice.appointment.exception;


import com.example.Timetable_microservice.timetable.exception.Validate;
import lombok.Getter;

@Getter
public class RecordLockedByAnotherUserExceptionCustomer extends RuntimeException {
    private final Validate error;

    public RecordLockedByAnotherUserExceptionCustomer(Validate error) {
        this.error = error;
    }

    public RecordLockedByAnotherUserExceptionCustomer(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public RecordLockedByAnotherUserExceptionCustomer(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public RecordLockedByAnotherUserExceptionCustomer(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public RecordLockedByAnotherUserExceptionCustomer(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
