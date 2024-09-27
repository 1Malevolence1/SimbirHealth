package com.example.Account_microservice.jwt.component;


import com.example.Account_microservice.data.ZoneDataService;
import com.example.Account_microservice.jwt.dto.JwtTokenIntrospectResponse;
import com.example.Account_microservice.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.jwt.service.JwtExtractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenIntrospectorImpl implements JwtTokenIntrospector {

    private final ZoneDataService zoneDataService;
    private final JwtExtractService jwtExtractService;
    private final BlackListTokenService blackListService;


    @Override
    public JwtTokenIntrospectResponse build(String token){

        return new JwtTokenIntrospectResponse(

                jwtExtractService.extractUserId(token),
                jwtExtractService.extractUserName(token),
                jwtExtractService.extractSubject(token),
                zoneDataService.getTime(jwtExtractService.extractIssuedAt(token)),
                zoneDataService.getTime(jwtExtractService.extractExpiration(token)),
                !blackListService.isTokenBlacklisted(token),
                jwtExtractService.extractRole(token));
    }
}
