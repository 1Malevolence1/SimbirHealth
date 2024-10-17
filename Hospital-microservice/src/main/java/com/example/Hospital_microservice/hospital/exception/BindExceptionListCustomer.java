package com.example.Hospital_microservice.hospital.exception;

import java.util.List;

public record BindExceptionListCustomer(
        List<Validate> errors
) {
}
