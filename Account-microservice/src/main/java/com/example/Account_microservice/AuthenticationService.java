package com.example.Account_microservice;

import com.example.Account_microservice.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInAccountDto;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(RequestSingUpAccountDto singUpDto);

    JwtAuthenticationResponse signIn(RequestSingInAccountDto singInDto);
}
