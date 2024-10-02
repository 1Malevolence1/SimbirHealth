package com.example.Account_microservice.controller;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.config.ConstantResponseSuccessfulText;
import com.example.Account_microservice.convert.manager_mapper.ManagerMapperAccount;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.serivice.admin.AdminService;
import com.example.Account_microservice.user.serivice.authorized_user.AuthorizedUser;
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


    private final AuthorizedUser authorizedUserService;
    private final JwtExtractService jwtExtractService;
    private final ManagerMapperAccount managerMapperAccount;
    private final AdminService adminService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Me")
    public ResponseEntity<ResponseAccountDto> getAccount(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);

        return ResponseEntity.ok().body(
                        authorizedUserService.findUserById(
                                jwtExtractService.extractUserId(token)
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

            authorizedUserService.update(
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
                managerMapperAccount.toDtoListAccount(
                        adminService.findUsersFromOffsetWithLimit(from, count))
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
            adminService.save(dto);
            return ResponseEntity.ok().build();
        }
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{accountId:\\d+}")
    public ResponseEntity<?> updateAdminAccount(@Valid @RequestBody RequestAdminUpdateAccount dto, BindingResult bindingResult,
                                                @PathVariable(name = "accountId") Long id) throws BindException {


        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {

            log.info("поулчил данные: {}", dto);
            adminService.update(dto, id);
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.SUCCESSFUL_ADMIN_UPDATE_ACCOUNT);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{accountId:\\d+}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "accountId") Long id) {
        log.info("начался метод по удалению пользователя ");
        adminService.deleteById(id);
        log.info("аккаунт удалён");
        return ResponseEntity.ok().body(ConstantResponseExceptionText.SUCCESSFUL_DELETE_USER.formatted(id));
    }

}
