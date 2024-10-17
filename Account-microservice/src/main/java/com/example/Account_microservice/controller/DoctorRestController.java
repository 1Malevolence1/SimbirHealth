package com.example.Account_microservice.controller;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.service.doctor.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Doctors", description = "все запросы начинающиеся с Doctors")
public class DoctorRestController {

    private final DoctorService doctorService;

    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение списка докторов", description = "Только авторизованные пользователи")
    @GetMapping()
    public ResponseEntity<List<ResponseDoctorDto>> getAllDoctor(@RequestParam(name = "nameFilter", required = false) String nameFilter,
                                                                @RequestParam(name = "from", required = false) Integer from,
                                                                @RequestParam(name = "count", required = false) Integer count) {

        return ResponseEntity.ok().body(doctorService.findAll(nameFilter, from, count));
    }


    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение информации о докторе по Id", description = "Только авторизованные пользователи")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", content = @Content(schema =  @Schema(implementation = ResponseDoctorDto.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(type = "string", example = ConstantResponseExceptionText.NOT_FOUND_DOCTOR_BY_ID)))
    }
    )
    @GetMapping("{doctorId:\\d+}")
    public ResponseEntity<ResponseDoctorDto> getDoctorById(@PathVariable(name = "doctorId") Long id) {
        return ResponseEntity.ok().body(
                doctorService.findById(id)
        );
    }
}
