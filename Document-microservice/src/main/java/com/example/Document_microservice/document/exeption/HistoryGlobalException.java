package com.example.Document_microservice.document.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class HistoryGlobalException {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<Validate>> handlerBindException(BindException bindException) {
        List<Validate> validates = bindException.getFieldErrors().stream().map(
                error -> new Validate(
                        error.getDefaultMessage().formatted(
                                error.getField()))
        ).toList();

        return ResponseEntity.badRequest().body(validates);
    }


    @ExceptionHandler(UserDoesNotHaveARole.class)
    public ResponseEntity<Validate> handlerUserDoesNotHaveARole(UserDoesNotHaveARole error) {
        return ResponseEntity.badRequest().body(error.getError());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Validate> handlerNoSuchElementException(NoSuchElementException error) {
        return ResponseEntity.badRequest().body(new Validate(error.getMessage()));
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Validate> handlerRoomNotFoundException(RoomNotFoundException error) {
        return ResponseEntity.badRequest().body(error.getError());
    }

    @ExceptionHandler(TheUserHasSeveralRoles.class)
    public ResponseEntity<Validate> handlerTheUserHasSeveralRoles(TheUserHasSeveralRoles error) {
        return ResponseEntity.badRequest().body(error.getError());
    }

    @ExceptionHandler(TheStoryDoesNotBelongToThisUser.class)
    public ResponseEntity<Validate> handlerTheStoryDoesNotBelongToThisUser(TheStoryDoesNotBelongToThisUser error) {
        return ResponseEntity.badRequest().body(error.getError());
    }
}
