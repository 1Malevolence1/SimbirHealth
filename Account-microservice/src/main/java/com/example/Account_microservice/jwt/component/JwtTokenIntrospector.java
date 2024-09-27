package com.example.Account_microservice.jwt.component;

import com.example.Account_microservice.jwt.dto.JwtTokenIntrospectResponse;

public interface JwtTokenIntrospector {
    JwtTokenIntrospectResponse build(String token);
}
