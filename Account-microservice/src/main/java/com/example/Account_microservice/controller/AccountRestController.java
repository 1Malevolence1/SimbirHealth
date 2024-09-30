package com.example.Account_microservice.controller;


import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.serivice.ConvertUser;
import com.example.Account_microservice.user.serivice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @PreAuthorize("isAuthenticated()")
    @PutMapping("/Update")
    public ResponseEntity<?> updateCurrentAccount(@RequestBody RequestUpdateAccountDto updateAccountDto,
                                                  BindingResult bindingResult,
                                                  HttpServletRequest request) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {

            String token = request.getHeader("Authorization").substring(7);

            userService.update(
                    updateAccountDto,
                    jwtExtractService.extractUserId(token)
            );

            return ResponseEntity.ok().body("Аккаунт успешно обновлён");
        }
    }
}
