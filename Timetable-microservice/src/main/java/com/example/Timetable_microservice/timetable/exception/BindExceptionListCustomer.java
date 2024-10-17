package com.example.Timetable_microservice.timetable.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BindExceptionListCustomer extends RuntimeException {
    public final List<Validate> errors;
}
