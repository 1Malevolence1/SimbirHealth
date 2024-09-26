package com.example.Account_microservice.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails userDetails);


    boolean isTokenValid(String token, UserDetails userDetails);

    Long getExpirationTime(String token);


    Boolean isTokenExpired(String token);


}
