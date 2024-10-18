package com.example.Account_microservice.user.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ManagerAuthorizationRequest {

    String getToken(HttpServletRequest request);
}
