package com.example.Account_microservice.controller;


import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.serivice.ConvertUser;
import com.example.Account_microservice.user.serivice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountRestController {


    private final UserService userService;
    private final JwtExtractService jwtExtractService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Me")
    public ResponseEntity<ResponseAccountDto> getAccount(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok().body(
                ConvertUser.toDTO(
                        userService.findUserById(
                                jwtExtractService.extractUserId(token)
                        )
                )
        );
    }
}
