package com.example.Timetable_microservice.timetable.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalTimetableException {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<Validate>> handlerBindException(BindException exception){
        List<Validate> errors = exception.getAllErrors().stream().map(
                error -> new Validate(error.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Validate> handlerNoSuchElementException(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Validate(exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Validate> handlerUnauthorizedException(UnauthorizedException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getValidate());
    }

    @ExceptionHandler(NoFindRoomInHospitalException.class)
    public ResponseEntity<Validate> handlerNoFindRoomWithHospitalExecution(NoFindRoomInHospitalException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getError());
    }

    @ExceptionHandler(BadUpdateTimetable.class)
    public ResponseEntity<Validate> handlerBadUpdateTimetable(BadUpdateTimetable exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getError());
    }

}
