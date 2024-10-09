package com.example.Account_microservice.controller;


import com.example.Account_microservice.security.jwt.dto.JwtDecongestingDtoResponse;
import com.example.Account_microservice.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
@Slf4j
public class JwtRestController {

    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<JwtDecongestingDtoResponse> decodingJwt(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        JwtDecongestingDtoResponse dto = jwtService.tokenDecoding(token);
        log.info("{}", dto);
        return ResponseEntity.ok().body(
                dto
        );
    }

}
