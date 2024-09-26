package com.example.Account_microservice.controller;


import com.example.Account_microservice.AuthenticationService;
import com.example.Account_microservice.exeption.BadRequestExceptionCustomer;
import com.example.Account_microservice.exeption.Validate;
import com.example.Account_microservice.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.user.dto.RequestSingInAccountDto;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.serivice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/SignOut")
    public ResponseEntity<?> signOut(HttpServletRequest request) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            request.getSession().invalidate();
        }
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<BadRequestExceptionCustomer> handlerBindExcept(BindException exception) {
        List<Validate> errors = exception.getAllErrors().stream().map(
                error -> new Validate(error.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(new BadRequestExceptionCustomer(errors));
    }

}
