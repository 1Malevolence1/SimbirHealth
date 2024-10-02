package com.example.Account_microservice.security;

import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInUserAccountDto;

public interface AuthenticationService {

    // JwtAuthenticationResponse signUp(RequestSingUpAccountDto singUpDto);

    JwtAuthenticationResponse signIn(RequestSingInUserAccountDto singInDto);

    JwtAuthenticationResponse refreshToken(String token);
}
