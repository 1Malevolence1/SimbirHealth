package com.example.Hospital_microservice.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestUpdateHospitalDto {
    private Long id;
    private String name;
    private String address;
    private String contactPhone;
    private List<String> rooms;

}
