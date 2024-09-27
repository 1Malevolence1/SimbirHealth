package com.example.Account_microservice.security.jwt.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;
import java.util.List;

public record JwtTokenIntrospectResponse(
        Long id,
        String username,
        String sub,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm::ss zzz yyyy")
        ZonedDateTime iat,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm::ss zzz yyyy")
        ZonedDateTime exp,
        Boolean active,
        List<JwtAuthority> role
) {
}
