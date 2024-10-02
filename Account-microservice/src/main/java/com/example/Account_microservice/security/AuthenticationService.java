package com.example.Account_microservice.security;

import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInUserAccountDto;
import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;

public interface AuthenticationService {

     JwtAuthenticationResponse signUp(RequestSingInGuestUserDto singUpDto);

    JwtAuthenticationResponse signIn(RequestSingInUserAccountDto singInDto);

    JwtAuthenticationResponse refreshToken(String token);
}
