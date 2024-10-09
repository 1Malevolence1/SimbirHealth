package com.example.Account_microservice.controller;


import com.example.Account_microservice.security.jwt.dto.JwtDecongestingDtoResponse;
import com.example.Account_microservice.security.jwt.dto.JwtDecongestingRolesDtoResponse;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.ResponseRolesUser;
import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
@Slf4j
public class JwtRestController {

    private final JwtService jwtService;
    private final JwtExtractService jwtExtractService;
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

        List<String> roles = userService.getRolesUserById(id).stream().map(Role::getRoleName).toList();
        return ResponseEntity.ok().body(new ResponseRolesUser(roles));
    }
}
