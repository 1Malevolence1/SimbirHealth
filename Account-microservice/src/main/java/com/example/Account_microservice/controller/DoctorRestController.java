package com.example.Account_microservice.controller;


import com.example.Account_microservice.user.dto.ResponseDoctorDto;
import com.example.Account_microservice.user.serivice.DoctorService;
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
    public ResponseEntity<List<ResponseDoctorDto>> getAllDoctor(@RequestParam(name = "nameFilter") String nameFilter,
                                                                @RequestParam(name = "from") Integer from,
                                                                @RequestParam(name = "count") Integer count) {

        return ResponseEntity.ok().body(doctorService.finaAll(nameFilter, from, count));
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("{doctorId:\\d+}")
    public ResponseEntity<ResponseDoctorDto> getDoctorById(@PathVariable(name = "doctorId") Long id) {
        return ResponseEntity.ok().body(
                doctorService.findById(id)
        );
    }
}
