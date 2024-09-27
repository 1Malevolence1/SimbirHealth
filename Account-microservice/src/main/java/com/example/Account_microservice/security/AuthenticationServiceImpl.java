package com.example.Account_microservice.security;


import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.RequestSingInAccountDto;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.serivice.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final JwtExtractService jwtExtractService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    // регестрация пользователя
    @Override
    public JwtAuthenticationResponse signUp(RequestSingUpAccountDto singUpDto) {

        User user = userService.save(singUpDto);
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    // @TODO обработать authenticationManager
    @Override
    public JwtAuthenticationResponse signIn(RequestSingInAccountDto singInDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (
                        singInDto.username(),
                        singInDto.password())
                );


        UserDetails user = userDetailsService.loadUserByUsername(singInDto.username());
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(String token) {
        Long userId = jwtExtractService.extractUserId(token);
        User user = userService.findUserById(userId);
        String refreshToken = jwtService.generateRefreshToken(user);
        log.info("---------------> {}", refreshToken.equals(token));
        return new JwtAuthenticationResponse(token);
    }



}
