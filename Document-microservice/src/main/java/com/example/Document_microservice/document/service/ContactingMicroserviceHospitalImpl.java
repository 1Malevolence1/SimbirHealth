package com.example.Document_microservice.document.service;


import com.example.Document_microservice.document.dto.ResponseRoomsHospitalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ContactingMicroserviceHospitalImpl implements ContactingMicroserviceHospital {

    private final RestClient restClientMicroserviceHospital;

    @Override
    public ResponseRoomsHospitalDto requestCheckHospital(String token, Long hospitalId) {
       try {

           ResponseEntity<ResponseRoomsHospitalDto> rooms = restClientMicroserviceHospital
                   .get()
                   .uri("/api/Hospitals/%d".formatted(hospitalId))
                   .header("Authorization", token)
                   .retrieve()
                   .toEntity(ResponseRoomsHospitalDto.class);

           return rooms.getBody();
       }catch (HttpClientErrorException exception){
           if(exception.getStatusCode() == HttpStatus.NOT_FOUND) throw new NoSuchElementException(exception.getMessage());
       }
       return null;
    }
}
