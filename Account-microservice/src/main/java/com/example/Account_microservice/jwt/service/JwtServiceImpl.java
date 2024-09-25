package com.example.Account_microservice.jwt.service;

import com.example.Account_microservice.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {


    private final String JWT_SING_IN_KEY = "53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855";


    // Генерация токена

    @Override
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        if(userDetails instanceof User customerUserDetails){
            claims.put("id", customerUserDetails.getId());
            claims.put("username", customerUserDetails.getUsername());
            claims.put("role", customerUserDetails.getAuthorities());
        }
        return createTokenJwt(claims, userDetails);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private String createTokenJwt(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 24)).
                signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }




    // Получение ключа для подписи токена
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SING_IN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
