package com.example.Hospital_microservice.security.jwt.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JwtServiceImpl implements JwtExtractService {

    private final String JWT_SIGN_IN_KEY = "53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855";

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Long extractUserId(String token) {
        return Long.parseLong(extractClaim(token, claims -> claims.get("jti").toString())); // Предполагаем, что "jti" - это ID пользователя
    }

    @Override
    public List<String> extractRoles(String token) {
        List<Map<String, String>> roles = extractClaim(token, claims -> (List<Map<String, String>>) claims.get("role"));
        return roles.stream()
                .map(roleMap -> roleMap.get("authority")) // Получаем значение "authority"
                .collect(Collectors.toList());
    }

    @Override
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUserName(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    // Получение ключа для подписи токена
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SIGN_IN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}