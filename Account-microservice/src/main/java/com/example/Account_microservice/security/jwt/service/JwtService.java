package com.example.Account_microservice.security.jwt.service;

import com.example.Account_microservice.security.jwt.dto.JwtDecongestingDtoResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);


    boolean isTokenValid(String token, UserDetails userDetails);


    boolean isTokenActive(String token);

    JwtDecongestingDtoResponse tokenDecoding(String token);
}
