package com.example.Timetable_microservice.timetable.service;

import com.example.Timetable_microservice.timetable.dto.UserIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
public class SearchingFieldsBetweenMicroservicesImpl implements SearchingFieldsBetweenMicroservices {

    private final RestClient restClientForUser;


    @Override
    public Long getUserId(String token) {
        ResponseEntity<UserIdDto> userId = restClientForUser
                .get().uri("/api/Accounts/Me")
                .header("Authorization", token)
                .retrieve().toEntity(UserIdDto.class);

        return userId.getBody().id();
    }
}
