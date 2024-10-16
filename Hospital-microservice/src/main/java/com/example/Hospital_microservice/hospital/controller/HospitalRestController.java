package com.example.Hospital_microservice.hospital.controller;


import com.example.Hospital_microservice.hospital.config.ConstantResponseSuccessfulText;
import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.RequestUpdateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.exception.Validate;
import com.example.Hospital_microservice.hospital.service.admin.AdminService;
import com.example.Hospital_microservice.hospital.service.authorized_user.AuthorizedUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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



    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Получение списка больниц", description = "Только авторизованные пользователи")
    @SecurityRequirement(name = "JWT")
    @GetMapping()
    public ResponseEntity<List<ResponseHospitalDto>> getAllHospitals(@RequestParam(name = "from", required = false) Integer from,
                                                                     @RequestParam(name = "count", required = false) Integer count){
        return ResponseEntity.ok().body(authorizedUserService.getAllHospitals(from, count));
    }


    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Получение информации о больнице по Id", description = "Только авторизованные пользователи")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseHospitalDto.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    @GetMapping({"/{hospitalId:\\d+}"})
    public ResponseEntity<ResponseHospitalDto> getHospitalById(@PathVariable(name = "hospitalId") Long id){
       return ResponseEntity.ok().body(
               authorizedUserService.getHospitalById(id)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Получение списка кабинетов больницы по Id", description = "Только авторизованные пользователи")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseHospitalRoomsDto.class)))),
            @ApiResponse(responseCode = "404", description = "Вернётся ошибка, если больницы с указаным id не будет существовать", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    @GetMapping("/{hospitalId:\\d+}/Rooms")
    public ResponseEntity<List<ResponseHospitalRoomsDto>> getRoomsByIdHospital(@PathVariable(name = "hospitalId") Long id) {
        return ResponseEntity.ok().body(authorizedUserService.getAllHospitalRooms(id));
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    @Operation(summary = "Создание записи о новой больнице", description = "Только администраторы")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "string", example = ConstantResponseSuccessfulText.CREATE_HOSPITAL))),
            @ApiResponse(responseCode = "400", description = "Вернётся список ошибок, если будут неккоректные данные", content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = Validate.class))))
    })
    @GetMapping("/{hospitalId:\\d+}/Rooms")
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

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping({"/{hospitalId:\\d+}"})
    @Operation(summary = "Изменение информации о больнице по Id", description = "Только администраторы")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "string", example = ConstantResponseSuccessfulText.UPDATE_HOSPITAL))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> updateHospitalById(@RequestBody RequestUpdateHospitalDto dto, @PathVariable(name = "hospitalId") Long id){
        adminService.updateHospital(dto, id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_HOSPITAL.formatted(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping({"/{hospitalId:\\d+}"})
    @Operation(summary = "Мягкое удаление записи о больнице", description = "Только администраторы")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<String> deleteHospitalById(@PathVariable(name = "hospitalId") Long id){
        adminService.deleteHospital(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_HOSPITAL.formatted(id));
    }
}
