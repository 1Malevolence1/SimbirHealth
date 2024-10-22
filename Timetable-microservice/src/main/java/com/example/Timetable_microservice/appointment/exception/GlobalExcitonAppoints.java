package com.example.Timetable_microservice.appointment.exception;


import com.example.Timetable_microservice.timetable.exception.Validate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcitonAppoints {


    @ExceptionHandler(RecordLockedByAnotherUserExceptionCustomer.class)
    public ResponseEntity<Validate> handlerRecordLockedByAnotherUserExceptionCustomer(RecordLockedByAnotherUserExceptionCustomer exception){
        return ResponseEntity.badRequest().body(exception.getError());
    }

    @ExceptionHandler(RecordUserExceptionCustomer.class)
    public ResponseEntity<Validate> handlerRecordUserExceptionCustomer(RecordUserExceptionCustomer exception){
        return ResponseEntity.badRequest().body(exception.getValidate());
    }
}

