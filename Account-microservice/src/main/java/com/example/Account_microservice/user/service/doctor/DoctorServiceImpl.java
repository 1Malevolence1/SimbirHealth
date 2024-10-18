package com.example.Account_microservice.user.service.doctor;

import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.user.convert.mapper.doctor.MapperDoctor;
import com.example.Account_microservice.user.convert.mapper.doctor.MapperListDoctor;
import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.repository.DoctorRepository;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {


    private final UserService userService;
    private final DoctorRepository doctorRepository;
    private final MapperListDoctor doctorListMapper;
    private final MapperDoctor doctorMapper;


    @Override
    public List<ResponseDoctorDto> findAll(String filterName, Integer from, Integer count) {

        int offset = (from != null) ? from : 0;
        int limit = (count != null) ? count : Integer.MAX_VALUE;

        if (filterName == null || filterName.isEmpty()) {

            return doctorListMapper.toDTO(doctorRepository.findAllDoctorsWithoutNameFilter(offset, limit));
        } else {

            return doctorListMapper.toDTO(doctorRepository.findAllDoctors(filterName, offset, limit));
        }
    }

    @Override
    public ResponseDoctorDto findById(Long id) {
        return doctorMapper.toDTO(doctorRepository.findDoctorByIdReturnOption(id).orElseThrow(() -> new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_DOCTOR_BY_ID.formatted(id))));
    }
}
