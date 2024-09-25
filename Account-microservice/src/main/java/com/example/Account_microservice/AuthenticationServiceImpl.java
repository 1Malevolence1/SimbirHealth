package com.example.Account_microservice;


import com.example.Account_microservice.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.RequestSingInUserDto;
import com.example.Account_microservice.user.dto.RequestSingUpUserDto;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.serivice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    // регестрация пользователя
    @Override
    public JwtAuthenticationResponse signUp(RequestSingUpUserDto singUpDto) {

        User user = userService.save(singUpDto);
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(RequestSingInUserDto singInDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (
                        singInDto.username(),
                        singInDto.password())
                );


        UserDetails user = userDetailsService.loadUserByUsername(singInDto.username());
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    // Аутентификация пользователя


}
