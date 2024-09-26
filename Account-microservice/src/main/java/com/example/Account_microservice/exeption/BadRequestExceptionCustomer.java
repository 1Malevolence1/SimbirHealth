package com.example.Account_microservice.exeption;


import java.util.List;


public record BadRequestExceptionCustomer(
        List<Validate> errors)  {
}
