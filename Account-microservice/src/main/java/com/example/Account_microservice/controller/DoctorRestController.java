package com.example.Account_microservice.controller;


import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Doctors")
@RequiredArgsConstructor
@Slf4j
public class DoctorRestController {

    private final DoctorService doctorService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public ResponseEntity<List<ResponseDoctorDto>> getAllDoctor(@RequestParam(name = "nameFilter", required = false) String nameFilter,
                                                                @RequestParam(name = "from", required = false) Integer from,
                                                                @RequestParam(name = "count", required = false) Integer count) {

        return ResponseEntity.ok().body(doctorService.findAll(nameFilter, from, count));
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("{doctorId:\\d+}")
    public ResponseEntity<ResponseDoctorDto> getDoctorById(@PathVariable(name = "doctorId") Long id) {
        return ResponseEntity.ok().body(
                doctorService.findById(id)
        );
    }
}
