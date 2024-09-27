package com.example.Account_microservice.jwt.dto;


import com.example.Account_microservice.user.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
