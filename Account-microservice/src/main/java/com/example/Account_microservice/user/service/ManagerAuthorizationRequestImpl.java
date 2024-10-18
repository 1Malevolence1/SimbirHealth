package com.example.Account_microservice.user.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public final class ManagerAuthorizationRequestImpl implements ManagerAuthorizationRequest {

    @Override
    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }
}
