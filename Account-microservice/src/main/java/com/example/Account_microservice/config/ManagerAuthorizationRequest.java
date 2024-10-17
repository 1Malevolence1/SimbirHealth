package com.example.Account_microservice.config;

import jakarta.servlet.http.HttpServletRequest;

public interface ManagerAuthorizationRequest {

    String getToken(HttpServletRequest request);
}
