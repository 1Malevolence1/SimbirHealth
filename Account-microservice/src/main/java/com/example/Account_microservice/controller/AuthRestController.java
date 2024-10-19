package com.example.Account_microservice.controller;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.config.ConstantResponseSuccessfulText;
import com.example.Account_microservice.security.AuthenticationService;
import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.security.jwt.dto.JwtRefreshTokenRequest;
import com.example.Account_microservice.security.jwt.exception.ValidateToken;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.RequestSingInUserAccountDto;
import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
import com.example.Account_microservice.user.exception.Validate;
import com.example.Account_microservice.user.service.ManagerAuthorizationRequest;
import com.example.Account_microservice.user.service.guest_user.GuestUserService;
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

@RestController
@RequestMapping("/api/Authentication")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "все запросы начинающиеся с Authentication")
public class AuthRestController {

    private final AuthenticationService authenticationService;
    private final GuestUserService guestUserService;
    private final JwtService jwtService;
    private final JwtExtractService extractService;
    private final BlackListTokenService blackListService;
    private final ManagerAuthorizationRequest managerAuthorizationRequest;


    @PostMapping("/SignUp")
    @Operation(summary = "Регестрация нового аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Польователь добавлен в базу данных", content = @Content(schema = @Schema(type = "string",example = ConstantResponseSuccessfulText.REGISTER_NEW_USER))),
            @ApiResponse(responseCode = "400", description = "Вернётся список полей, в которых не верно указанны данные",
            content = @Content(array =  @ArraySchema(schema = @Schema(implementation = Validate.class))))
    })
    public ResponseEntity<?> singUp(@Valid @RequestBody RequestSingInGuestUserDto singUpGuestUserDto,
                                    BindingResult bindingResult) throws BindException {
        log.info("данные для регестрации: {}", singUpGuestUserDto);
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else throw new BindException(bindingResult);
        } else {

            authenticationService.signUp(singUpGuestUserDto);
            log.info("аккаунт зарегестрирован");
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.REGISTER_NEW_USER);
        }
    }


    @PostMapping("/SignIn")
    @Operation(summary = "Получение новой пары jwt пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "получим токен"),
            @ApiResponse(responseCode = "404", description = ConstantResponseExceptionText.INVALID_CREDENTIALS_MESSAGE,
                    content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody RequestSingInUserAccountDto singInDot) {
        log.info("данные для аунтефикации: {}", singInDot);
        JwtAuthenticationResponse jwt = authenticationService.signIn(singInDot);
        log.info("аунитфикация прошла успено");
        return ResponseEntity.ok(jwt);
    }


    @PreAuthorize("isAuthenticated()")
    @PutMapping("/SignOut")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Выход из аккаунта", description = "Только авторизованные пользователи. После выхода из аккаунта, токен поподает в чёрный списко токенов. Испльзовать его потоврно будет нельзя")
    public ResponseEntity<?> signOut(HttpServletRequest request) {


        String token = managerAuthorizationRequest.getToken(request);

        blackListService.save(
                new BlackListTokenDto(
                        token,
                        extractService.extractExpirationGetLocalDataTime(token)
                )
        );
        return ResponseEntity.ok(ConstantResponseSuccessfulText.SING_OUT_USER_OK);
    }


    @GetMapping("/Validate")
    @Operation(summary = "Интроспекция токена")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "ошибка, возникшая при обработке токена",
                    content =
                    @Content(schema = @Schema(oneOf = {ValidateToken.class, Validate.class}))),
            @ApiResponse(responseCode = "200", description = "вернёт статуст 200, если нет ошибок")})
    public ResponseEntity<?> introspect(@RequestParam(name = "accessToken") String token) {
        log.info("начался мето проверки");
        jwtService.isTokenActive(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/Refresh")
    @Operation(summary = "Обновление пары токенов",  description ="Cтарый токен поподает в чёрный списко токенов. Испльзовать его потоврно будет нельзя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "ошибка, возникшая при обработке токена",
                    content =
                    @Content(schema = @Schema(oneOf = {ValidateToken.class, Validate.class}))),
            @ApiResponse(responseCode = "200", description = "вернёт новый токен")})
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(jwtRefreshTokenRequest.refreshToken()));
    }
}

