package com.example.Account_microservice.security.jwt.dto;

import java.util.List;

public record JwtDecongestingRolesDtoResponse(
    List<String> roles)
{
}
