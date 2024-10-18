package com.example.Account_microservice.security.jwt.service;

import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.security.jwt.dto.JwtAuthority;
import com.example.Account_microservice.security.jwt.dto.JwtDecongestingDtoResponse;
import com.example.Account_microservice.security.jwt.exception.BadDataTokenCustomerException;
import com.example.Account_microservice.security.jwt.exception.TokenBlackListException;
import com.example.Account_microservice.security.jwt.exception.ValidateToken;
import com.example.Account_microservice.user.exception.Validate;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService, JwtExtractService {


    private final String JWT_SING_IN_KEY = "53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855";
    private final UserService userService;
    private final BlackListTokenService blackListTokenService;

    // Генерация токена


    @Override
    public String extractUserName(String token) {
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
    public LocalDateTime extractExpirationGetLocalDataTime(String token) {
        return extractExpiration(token).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    @Override
    public List<JwtAuthority> extractRole(String token) {

        List<Map<String, String>> roles = extractClaim(token, claims -> (List<Map<String, String>>) claims.get("role"));

        return roles.stream()
                .map(roleMap -> new JwtAuthority(roleMap.get("authority")))
                .collect(Collectors.toList());
    }


    @Override
    public List<String> extractRolesString(String token) {
        return extractRole(token).stream().map(JwtAuthority::authority).toList();
    }


    public String extractOneRole(String token) {

        List<Map<String, String>> roles = extractClaim(token, claims -> (List<Map<String, String>>) claims.get("role"));

        return roles.stream()
                .map(roleMap -> roleMap.get("authority"))
                .toList().getFirst();
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


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }


    @Override
    public void isTokenActive(String token) {

        if (blackListTokenService.isTokenBlacklisted(token)) {
            throw new TokenBlackListException(new ValidateToken(
                    ConstantResponseExceptionText.VALIDATE_TOKEN_BLACK_LIST,
                    token
            ));
        }


        if (token == null || isTokenExpired(token)) {
            throw new BadDataTokenCustomerException(new Validate(ConstantResponseExceptionText.BAD_TOKEN));
        }
        Long userId = extractUserId(token);
        User user = userService.findUserById(userId);
        if (user == null || user.getDeleted()) {
            throw new BadDataTokenCustomerException(new Validate(ConstantResponseExceptionText.BAD_TOKEN));
        }
    }


    @Override
    public JwtDecongestingDtoResponse tokenDecoding(String token) {
        return new JwtDecongestingDtoResponse(
                extractUserId(token),
                extractUserName(token),
                extractOneRole(token)
        );
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SING_IN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
