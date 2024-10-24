package com.example.Account_microservice.security;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.security.jwt.dto.JwtAuthenticationResponse;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
import com.example.Account_microservice.user.dto.RequestSingInUserAccountDto;
import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
import com.example.Account_microservice.user.exception.BadRequestSingInCustomer;
import com.example.Account_microservice.user.exception.Validate;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.service.guest_user.GuestUserService;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final GuestUserService guestUserService;
    private final JwtService jwtService;
    private final JwtExtractService jwtExtractService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final BlackListTokenService blackListTokenService;


    @Override
    public void signUp(RequestSingInGuestUserDto singUpDto) {
        guestUserService.addAccount(singUpDto);
    }


    @Override
    public JwtAuthenticationResponse signIn(RequestSingInUserAccountDto singInDto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    singInDto.username(),
                    singInDto.password())
            );

        } catch (BadCredentialsException e) {
            throw new BadRequestSingInCustomer(new Validate(ConstantResponseExceptionText.INVALID_CREDENTIALS_MESSAGE)
            );
        }

        UserDetails user = userDetailsService.loadUserByUsername(singInDto.username());
        return jwtService.generatePairJwtToken(user);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(String oldRefreshToken) {
        jwtService.isTokenActive(oldRefreshToken);
        blackListTokenService.save(
                new BlackListTokenDto(
                        oldRefreshToken,
                        jwtExtractService.extractExpirationGetLocalDataTime((oldRefreshToken)
                        )));
        Long userId = jwtExtractService.extractUserId(oldRefreshToken);
        User user = userService.findUserById(userId);
        return jwtService.generatePairJwtToken(user);
    }


}
