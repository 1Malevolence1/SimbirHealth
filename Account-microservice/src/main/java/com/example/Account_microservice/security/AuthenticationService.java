package com.example.Account_microservice.security;

import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInAccountDto;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;

public interface AuthenticationService {

    // JwtAuthenticationResponse signUp(RequestSingUpAccountDto singUpDto);

    JwtAuthenticationResponse signIn(RequestSingInAccountDto singInDto);

    JwtAuthenticationResponse refreshToken(String token);
}
