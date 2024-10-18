package com.example.Account_microservice.security.jwt.filter;

import com.example.Account_microservice.security.CustomerUserDetailsService;
import com.example.Account_microservice.security.jwt.black_list.service.BlackListTokenService;
import com.example.Account_microservice.security.jwt.service.JwtExtractService;
import com.example.Account_microservice.security.jwt.service.JwtService;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authHeader.substring(BEARER_PREFIX.length());


        if (blackListTokenService.isTokenBlacklisted(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен недействителен.");
            log.error("токен нахоидтся в black list");
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

            if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails;
                try {
                     userDetails = customerUserDetailsService.loadUserByUsername(username);
                } catch (UsernameNotFoundException exception){
                    log.error(exception.getMessage());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
                    return;
                }


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


            filterChain.doFilter(request, response);
        }
    }

