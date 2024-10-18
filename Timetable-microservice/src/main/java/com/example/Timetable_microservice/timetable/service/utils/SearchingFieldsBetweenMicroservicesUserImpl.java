package com.example.Timetable_microservice.timetable.service.utils;

import com.example.Timetable_microservice.timetable.dto.user.UserIdDto;
import com.example.Timetable_microservice.timetable.dto.user.UserRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
public class SearchingFieldsBetweenMicroservicesUserImpl implements SearchingFieldsBetweenMicroservicesUser {

    private final RestClient restClientForUser;


    @Override
    public Long getUserId(String token) {
        ResponseEntity<UserIdDto> userId = restClientForUser
                .get().uri("/api/Accounts/Me")
                .header("Authorization", token)
                .retrieve().toEntity(UserIdDto.class);

        return userId.getBody().id();
    }

    @Override
    public String getRole(String token) {
        ResponseEntity<UserRoleDto> role = restClientForUser
                .get().uri("/api/jwt")
                .header("Authorization", token)
                .retrieve().toEntity(UserRoleDto.class);
        return role.getBody().role();
    }
}
