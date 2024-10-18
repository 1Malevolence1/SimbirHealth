package com.example.Account_microservice.user.exception;


import java.util.List;


public record BadRequestExceptionCustomer(
        List<Validate> errors)  {
}
