package com.example.Timetable_microservice.security.jwt;


import com.example.Timetable_microservice.security.jwt.service.JwtExtractService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final RestTemplate restTemplate;
    private final JwtExtractService jwtExtractService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authHeader.substring(BEARER_PREFIX.length());


        Map<String, String> params = new HashMap<>();
        params.put("accessToken", jwt);
        ResponseEntity<Map> validationResponse;

        try {
            validationResponse = restTemplate.getForEntity("http://localhost:8081/api/Authentication/Validate?accessToken={accessToken}", Map.class, params);
            if (validationResponse.getBody() == null || !Boolean.TRUE.equals(validationResponse.getBody().get("active"))) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен недействителен.");
                return;
            }
        } catch (Exception e) {
            log.error("Ошибка при валидации токена: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ошибка при валидации токена.");
            return;
        }

        String username = jwtExtractService.extractUserName(jwt);

        List<String> roles = jwtExtractService.extractRoles(jwt);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(SimpleGrantedAuthority::new).toList();


        if (StringUtils.isNotEmpty(username)) {

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }


     else {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен недействителен.");
        return;
    }



        filterChain.doFilter(request, response);
    }
}

