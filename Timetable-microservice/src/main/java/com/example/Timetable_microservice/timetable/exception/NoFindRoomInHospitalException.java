package com.example.Timetable_microservice.timetable.exception;

import lombok.Getter;

@Getter
public class NoFindRoomInHospitalException extends RuntimeException{

    private final Validate error;

    public NoFindRoomInHospitalException(Validate error) {
        this.error = error;
    }

    public NoFindRoomInHospitalException(String message, Validate error) {
        super(message);
        this.error = error;
    }

    public NoFindRoomInHospitalException(String message, Throwable cause, Validate error) {
        super(message, cause);
        this.error = error;
    }

    public NoFindRoomInHospitalException(Throwable cause, Validate error) {
        super(cause);
        this.error = error;
    }

    public NoFindRoomInHospitalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Validate error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
