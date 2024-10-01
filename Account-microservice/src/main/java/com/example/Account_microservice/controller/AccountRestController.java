package com.example.Account_microservice.controller;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.mapper.MapperListUser;
import com.example.Account_microservice.mapper.MapperUser;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.serivice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountRestController {


    private final UserService userService;
    private final JwtExtractService jwtExtractService;
    private final MapperListUser mapperListUser;
    private final MapperUser mapperUser;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Me")
    public ResponseEntity<ResponseAccountDto> getAccount(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);

        return ResponseEntity.ok().body(
                mapperUser.toDTO(
                        userService.findUserById(
                                jwtExtractService.extractUserId(token)
                        )
                )
        );
    }


    @PreAuthorize("isAuthenticated()")
    @PutMapping("/Update")
    public ResponseEntity<?> updateCurrentAccount(@Valid @RequestBody RequestUpdateAccountDto updateAccountDto,
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


    // TODO добавить мапстракт для переделывания в юзера в дто

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<ResponseAccountDto>> getAllUser(
            @RequestParam(name = "from") Integer from,
            @RequestParam(name = "count") Integer count) {
        return ResponseEntity.ok().body(
                mapperListUser.toDTO(
                        userService.findUsersFromOffsetWithLimit(from, count))
        );
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<?> savaAdminNweUser(@Valid @RequestBody RequestAdminSaveAccount dto, BindingResult bindingResult) throws BindException {


        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {


            log.info("поулчил данные: {}", dto);
            userService.saveAdmin(dto);
            return ResponseEntity.ok().build();
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{accountId:\\d+}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "accountId") Long id) {
        log.info("начался метод по удалению пользователя ");
        userService.deleteById(id);
        log.info("аккаунт удалён");
        return ResponseEntity.ok().body(ConstantResponseExceptionText.SUCCESSFUL_DELETE_USER.formatted(id));
    }

}
