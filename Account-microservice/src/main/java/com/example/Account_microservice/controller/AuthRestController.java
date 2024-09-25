package com.example.Account_microservice.controller;


import com.example.Account_microservice.AuthenticationService;
import com.example.Account_microservice.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInUserDto;
import com.example.Account_microservice.user.dto.RequestSingUpUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Authentication")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/SignUp")
    public ResponseEntity<JwtAuthenticationResponse> singUp(@RequestBody RequestSingUpUserDto dto){
        JwtAuthenticationResponse jwt = authenticationService.signUp(dto);
        return ResponseEntity.ok(jwt);
    }


    @PostMapping("/SignIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody RequestSingInUserDto dto){
        JwtAuthenticationResponse jwt = authenticationService.signIn(dto);
        return ResponseEntity.ok(jwt);
    }

}
