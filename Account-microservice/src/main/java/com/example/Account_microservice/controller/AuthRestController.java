package com.example.Account_microservice.controller;


import com.example.Account_microservice.security.AuthenticationService;
import com.example.Account_microservice.config.ConstantResponseText;
import com.example.Account_microservice.exception.BadRequestExceptionCustomer;
import com.example.Account_microservice.exception.Validate;
import com.example.Account_microservice.security.jwt.component.JwtTokenIntrospector;
import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.security.jwt.black_list.model.BlackListToken;
import com.example.Account_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.security.jwt.dto.JwtRefreshTokeRequest;
import com.example.Account_microservice.security.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.RequestSingInAccountDto;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.serivice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Authentication")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;
    private final BlackListTokenService blackListService;
    private final JwtTokenIntrospector jwtTokenIntrospector;




    @PostMapping("/SignUp")
    public ResponseEntity<?> singUp(@Valid @RequestBody RequestSingUpAccountDto singUpDto,
                                    BindingResult bindingResult) throws BindException {
        log.info("данные для регестрации: {}", singUpDto);
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else throw new BindException(bindingResult);
        } else {

            userService.save(singUpDto);
            log.info("аккаунт зарегестрирован");
            return ResponseEntity.ok().body("Аккаунт успешно зарегестрирован");
        }
    }


    @PostMapping("/SignIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody RequestSingInAccountDto singInDot) {
        log.info("данные для аунтефикации: {}", singInDot);
        JwtAuthenticationResponse jwt = authenticationService.signIn(singInDot);
        log.info("аунитфикация прошла успено");
        return ResponseEntity.ok(jwt);
    }


    @PreAuthorize("isAuthenticated()")
    @PutMapping("/SignOut")
    public ResponseEntity<?> signOut(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

            String token = request.getHeader("Authorization").substring(7);
            log.info("токен: {}", token);
                blackListService.save(new BlackListToken(
                        null,
                        token,
                        jwtService.getExpirationTime(token)));
                return ResponseEntity.ok(ConstantResponseText.SING_OUT_USER_OK);

        }
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ConstantResponseText.SING_OUT_USER_UNAUTHORIZED);
    }


    @GetMapping("/Validate")
    public ResponseEntity<?> introspect(@RequestParam(name = "accessToken") String token){
        log.info("->>>>>{}", token);
        return ResponseEntity.ok().body(
                jwtTokenIntrospector.build(token)
        );
    }



    @PostMapping("/Refresh")
    public ResponseEntity<?> refreshToken(@RequestBody JwtRefreshTokeRequest jwtRefreshTokeRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(jwtRefreshTokeRequest.refreshToken()));
    }


}
