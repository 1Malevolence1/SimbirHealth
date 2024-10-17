package com.example.Timetable_microservice.security.jwt;


import com.example.Timetable_microservice.security.jwt.dto.ResponseDataUserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final RestClient restClientSecurity;



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


        try {
            ResponseEntity<?> responseEntity = restClientSecurity.get().uri("/api/Authentication/Validate?accessToken=%s".formatted(jwt)).retrieve().toEntity(Void.class);
            if (responseEntity.getStatusCode() == HttpStatus.FORBIDDEN) {

                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен недействителен.");

                return;
            }
        } catch (Exception e) {
            log.error("Ошибка при валидации токена");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ошибка при валидации токена.");
            return;
        }

        ResponseEntity<ResponseDataUserDto> responseDataUserDto = restClientSecurity.get().uri("/api/jwt/dataUser").header("Authorization", authHeader).retrieve().toEntity(ResponseDataUserDto.class);
        String username = responseDataUserDto.getBody().username();
        List<String> roles = responseDataUserDto.getBody().roles();
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

