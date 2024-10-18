package com.example.Account_microservice.controller;


import com.example.Account_microservice.security.jwt.dto.JwtDecongestingDtoResponse;
import com.example.Account_microservice.security.jwt.dto.ResponseDataUserDto;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.ResponseRolesUser;
import com.example.Account_microservice.user.service.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
@Slf4j
@Hidden
public class JwtDecodingRestController {

    private final JwtService jwtService;
    private final JwtExtractService extractService;

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<JwtDecongestingDtoResponse> decodingAllJwt(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        JwtDecongestingDtoResponse dto = jwtService.tokenDecoding(token);
        log.info("{}", dto);
        return ResponseEntity.ok().body(
                dto
        );
    }

    @GetMapping("/roles/{accountId:\\d+}")
    public ResponseEntity<ResponseRolesUser> getDecodingJwtRoles(@PathVariable("accountId") Long id) {
        List<String> roles = userService.getRolesUserById(id);
        log.info("{}" , roles);
        return ResponseEntity.ok().body(new ResponseRolesUser(roles));
    }



    @GetMapping("/dataUser")
    public ResponseEntity<ResponseDataUserDto> getDataUser(@RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);

        return ResponseEntity.ok().body(
                new ResponseDataUserDto(
                        extractService.extractUserName(token),
                        extractService.extractRolesString(token)
                )
        );
    }
}
