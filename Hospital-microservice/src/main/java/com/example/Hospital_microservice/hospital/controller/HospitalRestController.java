package com.example.Hospital_microservice.hospital.controller;


import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalRestController {

    private final AdminService adminService;


    @GetMapping()
    public ResponseEntity<List<ResponseHospitalDto>> getAllHospitals(@RequestParam(name = "from", required = false) Integer from,
                                                                     @RequestParam(name = "count", required = false) Integer count){
        return ResponseEntity.ok().body(adminService.getAllHospitals(from, count));
    }


    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<String> addAdminHospital(@Valid @RequestBody RequestCreateHospitalDto dto,
                                                   BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException e) {
                throw e;
            } else {
                throw new BindException(bindingResult);
            }
        } else {

            log.info("Получил данные {}", dto);
            adminService.addHospital(dto);

            return ResponseEntity.ok().body("Больница успешно добавлена");
        }
    }
}
