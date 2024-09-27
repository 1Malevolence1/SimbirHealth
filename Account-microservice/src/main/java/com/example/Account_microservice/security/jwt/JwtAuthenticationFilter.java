package com.example.Account_microservice.security.jwt;

import com.example.Account_microservice.security.CustomerUserDetailsService;
import com.example.Account_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final JwtService jwtService;
    private final JwtExtractService jwtExtractService;
    private final BlackListTokenService blackListTokenService;
    private final CustomerUserDetailsService customerUserDetailsService;

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

        // Проверяем наличие токена в черном списке
        if (blackListTokenService.isTokenBlacklisted(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен недействителен.");
            return; // Прерываем выполнение, если токен недействителен
        }

        if (jwtExtractService.isTokenExpired(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен истек.");
            return;
        }

        // Извлекаем имя пользователя из токена
        var username = jwtExtractService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

            // Если токен валиден, то аутентифицируем пользователя
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Продолжаем обработку запроса
        filterChain.doFilter(request, response);
    }
}
