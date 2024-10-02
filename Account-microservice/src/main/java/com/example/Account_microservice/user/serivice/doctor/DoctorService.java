package com.example.Account_microservice.user.serivice.doctor;


import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;

import java.util.List;

public interface DoctorService {

  List<ResponseDoctorDto> finaAll(String filterName, Integer form, Integer count);

  ResponseDoctorDto findById(Long id);
}
