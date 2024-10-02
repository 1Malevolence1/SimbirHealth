package com.example.Account_microservice.security.jwt.service;

import com.example.Account_microservice.security.jwt.dto.JwtAuthority;
import com.example.Account_microservice.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService, JwtExtractService {


    private final String JWT_SING_IN_KEY = "53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855";


    // Генерация токена


    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Long extractUserId(String token) {
        return Long.parseLong(extractClaim(token, Claims::getId));

    }

    @Override
    public Date extractIssuedAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }



    @Override
    public List<JwtAuthority> extractRole(String token) {

        List<Map<String, String>> roles = extractClaim(token, claims -> (List<Map<String, String>>) claims.get("role"));

        return roles.stream()
                .map(roleMap -> new JwtAuthority(roleMap.get("authority")))
                .collect(Collectors.toList());
    }




    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customerUserDetails) {
            claims.put("jti", String.valueOf(customerUserDetails.getId()));
            claims.put("username", customerUserDetails.getUsername());
            claims.put("role", customerUserDetails.getAuthorities());
        }
        return createTokenJwt(claims, userDetails);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customerUserDetails) {
            claims.put("jti", String.valueOf(customerUserDetails.getId()));
            claims.put("username", customerUserDetails.getUsername());
            claims.put("role", customerUserDetails.getAuthorities());
        }
        return createTokenJwt(claims, userDetails);

    }

    private String createTokenJwt(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 24)).
                signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }



    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }



    @Override
    public Long getExpirationTime(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.getTime() - System.currentTimeMillis();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }




    // Получение ключа для подписи токена
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SING_IN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
