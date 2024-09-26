package com.example.Account_microservice.jwt.dto;


import com.example.Account_microservice.user.model.Role;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record JwtTokenIntrospectResponse(
        Long id,
        String username,
        String sub,
        Date iat,
        Date exp,
        List<JwtAuthority> role
) {
}
