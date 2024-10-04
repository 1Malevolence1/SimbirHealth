package com.example.Hospital_microservice.hospital.controller;


import com.example.Hospital_microservice.hospital.config.ConstantResponseSuccessfulText;
import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.RequestUpdateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.service.admin.AdminService;
import com.example.Hospital_microservice.hospital.service.authorized_user.AuthorizedUserService;
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
    private final AuthorizedUserService authorizedUserService;

    @GetMapping()
    public ResponseEntity<List<ResponseHospitalDto>> getAllHospitals(@RequestParam(name = "from", required = false) Integer from,
                                                                     @RequestParam(name = "count", required = false) Integer count){
        return ResponseEntity.ok().body(authorizedUserService.getAllHospitals(from, count));
    }

    @GetMapping({"/{hospitalId:\\d+}"})
    public ResponseEntity<ResponseHospitalDto> getHospitalById(@PathVariable(name = "hospitalId") Long id){
       return ResponseEntity.ok().body(
               authorizedUserService.getHospitalById(id)
        );
    }


    @GetMapping("/{hospitalId:\\d+}/Rooms")
    public ResponseEntity<List<ResponseHospitalRoomsDto>> getRoomsByIdHospital(@PathVariable(name = "hospitalId") Long id) {
        return ResponseEntity.ok().body(authorizedUserService.getAllHospitalRooms(id));
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


    @PutMapping({"/{hospitalId:\\d+}"})
    public ResponseEntity<String> updateHospitalById(@RequestBody RequestUpdateHospitalDto dto, @PathVariable(name = "hospitalId") Long id){
        dto.setId(id);
        adminService.updateHospital(dto);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_HOSPITAL.formatted(id));
    }


    @DeleteMapping({"/{hospitalId:\\d+}"})
    public ResponseEntity<String> deleteHospitalById(@PathVariable(name = "hospitalId") Long id){
        adminService.deleteHospital(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_HOSPITAL.formatted(id));
    }
}
