package com.example.Account_microservice.exception;


import java.util.List;


public record BadRequestExceptionCustomer(
        List<Validate> errors)  {
}
