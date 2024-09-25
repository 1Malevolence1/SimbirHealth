package com.example.Account_microservice;

import com.example.Account_microservice.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInUserDto;
import com.example.Account_microservice.user.dto.RequestSingUpUserDto;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(RequestSingUpUserDto singUpDto);

    JwtAuthenticationResponse signIn(RequestSingInUserDto singInDto);
}
