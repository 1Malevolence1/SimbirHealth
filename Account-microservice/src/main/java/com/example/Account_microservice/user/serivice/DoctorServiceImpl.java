package com.example.Account_microservice.user.serivice;

import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.mapper.doctor.DoctorListMapper;
import com.example.Account_microservice.mapper.doctor.DoctorMapper;
import com.example.Account_microservice.user.dto.ResponseDoctorDto;
import com.example.Account_microservice.user.repository.DoctorRepository;
import com.example.Account_microservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository userRepository;

    private final DoctorRepository doctorRepository;
    private final DoctorListMapper doctorListMapper;
    private final DoctorMapper doctorMapper;

    @Override
    public List<ResponseDoctorDto> finaAll(String filterName, Integer form, Integer count) {
        return doctorListMapper.toDTO(doctorRepository.findAllDoctors(filterName, form, count));
    }

    @Override
    public ResponseDoctorDto findById(Long id) {
        return doctorMapper.toDTO(userRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id))));
    }
}
