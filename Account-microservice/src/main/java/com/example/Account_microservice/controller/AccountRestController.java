package com.example.Account_microservice.controller;


import com.example.Account_microservice.config.ConstantResponseSuccessfulText;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.user.dto.RequestUpdateUserAccountDto;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
import com.example.Account_microservice.user.dto.admin.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.admin.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.exception.Validate;
import com.example.Account_microservice.user.service.ManagerAuthorizationRequest;
import com.example.Account_microservice.user.service.admin.AdminService;
import com.example.Account_microservice.user.service.authorized_user.AuthorizedUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Account", description = "все запросы начинающиеся с Account")
public class AccountRestController {


    private final AuthorizedUserService authorizedUserService;
    private final AdminService adminService;
    private final JwtExtractService jwtExtractService;
    private final ManagerAuthorizationRequest managerAuthorizationRequest;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Me")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение данных о текущем аккаунте", description = "Только авторизованные пользователи")
    public ResponseEntity<ResponseUserAccountDto> getOneUser(HttpServletRequest request) {

        String token = managerAuthorizationRequest.getToken(request);

        return ResponseEntity.ok().body(
                        authorizedUserService.findUserById(
                                jwtExtractService.extractUserId(token)
                        )

        );
    }


    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "JWT")
    @PutMapping("/Update")
    @Operation(summary = "Обновление своего аккаунта", description = "Только авторизованные пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "даныые обновлены", content = @Content(schema =  @Schema(type = "string", example = ConstantResponseSuccessfulText.UPDATE_ACCOUNT))),
            @ApiResponse(responseCode = "403")
    })
    public ResponseEntity<?> updateUserAccount(@RequestBody RequestUpdateUserAccountDto updateAccountDto,
                                                 HttpServletRequest request)  {

            String token = managerAuthorizationRequest.getToken(request);
            authorizedUserService.update(
                    updateAccountDto,
                    jwtExtractService.extractUserId(token)
            );

            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_ACCOUNT);
        }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение списка всех аккаунтов",description = "Только администраторы")
    @GetMapping()
    public ResponseEntity<List<ResponseUserAccountDto>> getAllUser(
                                             @RequestParam(name = "from", required = false) Integer from,
                                             @RequestParam(name = "count", required = false) Integer count){
        if(from == null) from = 0;
        return ResponseEntity.ok().body(
                        adminService.getAll(from, count)
        );
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "JWT")
    @PostMapping()
    @Operation(summary = "Создание администратором нового аккаунта", description = "Только администраторы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "400", description = "Вернётся список полей, в которых не верно указанны данные",
                    content = @Content(array =  @ArraySchema(schema = @Schema(implementation = Validate.class)))),
            @ApiResponse(responseCode = "200", description = "админ сохранил новый аккаунт")})
    public ResponseEntity<?> savaAdminNewUser(@Valid @RequestBody RequestAdminSaveAccount dto, BindingResult bindingResult) throws BindException {


        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {

            log.info("поулчил данные: {}", dto);
            adminService.save(dto);
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.SUCCESSFUL_ADMIN_SAVE_ACCOUNT);
        }
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "JWT")
    @PutMapping("{accountId:\\d+}")
    @Operation(summary = "Изменение администратором аккаунта по id", description = "Только администраторы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "200", description = "админ обновил новый аккаунт"),
            @ApiResponse(responseCode = "404", description = "пользователь не найден в базе данных", content = @Content(schema = @Schema(implementation = Validate.class))),})
    public ResponseEntity<?> updateAdminAccount(@RequestBody RequestAdminUpdateAccount dto,
                                                @PathVariable(name = "accountId") Long id)   {

            log.info("поулчил данные: {}", dto);
            adminService.update(dto, id);
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.SUCCESSFUL_ADMIN_UPDATE_ACCOUNT.formatted(id));
        }




    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Мягкое удаление аккаунта по id", description = "Только администраторы")
    @DeleteMapping("{accountId:\\d+}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "accountId") Long id) {
        log.info("начался метод по удалению пользователя ");
        adminService.deleteById(id);
        log.info("аккаунт удалён");
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.SUCCESSFUL_DELETE_USER.formatted(id));
    }

}
