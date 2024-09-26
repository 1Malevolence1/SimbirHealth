package com.example.Account_microservice.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
