package com.example.Account_microservice.security.jwt.component;

import com.example.Account_microservice.security.jwt.dto.JwtTokenIntrospectResponse;

public interface JwtTokenIntrospector {
    JwtTokenIntrospectResponse build(String token);
}
