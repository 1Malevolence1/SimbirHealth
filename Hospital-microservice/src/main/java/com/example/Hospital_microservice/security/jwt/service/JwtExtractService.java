package com.example.Hospital_microservice.security.jwt.service;


import javax.xml.crypto.Data;
import java.util.Date;

public interface JwtExtractService {

    Boolean isTokenExpired(String token);
    Date extractExpiration(String token);
}
