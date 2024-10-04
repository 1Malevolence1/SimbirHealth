package com.example.Hospital_microservice.security.jwt;

import com.example.Hospital_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Hospital_microservice.security.jwt.service.JwtExtractService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtExtractService jwtExtractService;
    private final BlackListTokenService blackListTokenService;
    private final UserDetailsService userDetailsService; // Добавлено для проверки ролей

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Получаем токен из заголовка
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return; // Если заголовок отсутствует или не начинается с "Bearer ", продолжаем обработку
        }

        // Обрезаем префикс и получаем токен
        var jwt = authHeader.substring(BEARER_PREFIX.length());


        if (blackListTokenService.isTokenBlacklisted(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен недействителен.");
            log.info("токен нахоидтся в black list");
            return;
        }

        try {
            if (jwtExtractService.isTokenExpired(jwt)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен истек.");
                return;
            }
        } catch (ExpiredJwtException e) {
            log.error("JWT expired: {}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Токен истек и больше не действителен.");
            log.error(("Токен истек и больше не действителен."));
        }

        var username = jwtExtractService.extractUserName(jwt);

        // Продолжаем обработку запроса
        filterChain.doFilter(request, response);
    }
}

