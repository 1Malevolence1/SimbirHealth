package com.example.Hospital_microservice.security.jwt.service;


import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface JwtExtractService {

    Boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    String extractUserName(String token);

    Long extractUserId(String token);

    List<String> extractRoles(String token);

    boolean isTokenValid(String token, String username);
}
