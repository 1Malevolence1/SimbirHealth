package com.example.Account_microservice.user.service.doctor;

import com.example.Account_microservice.convert.mapper.doctor.MapperDoctor;
import com.example.Account_microservice.convert.mapper.doctor.MapperListDoctor;
import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.repository.DoctorRepository;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {


    private final UserService userService;
    private final DoctorRepository doctorRepository;
    private final MapperListDoctor doctorListMapper;
    private final MapperDoctor doctorMapper;


    @Override
    public List<ResponseDoctorDto> finaAll(String filterName, Integer form, Integer count) {
        return doctorListMapper.toDTO(doctorRepository.findAllDoctors(filterName, form, count));
    }

    @Override
    public ResponseDoctorDto findById(Long id) {
        return doctorMapper.toDTO(userService.findUserById(id));
    }
}
