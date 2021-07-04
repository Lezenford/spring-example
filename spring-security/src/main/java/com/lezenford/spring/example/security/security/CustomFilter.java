package com.lezenford.spring.example.security.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class CustomFilter extends OncePerRequestFilter {
    private static final String AUTH_PREFIX = "Custom";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException,
            IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(AUTH_PREFIX)) {
            final String token = header.replaceFirst(AUTH_PREFIX + " ", "");
            final String[] credentials = new String(Base64.getDecoder().decode(token)).split(":");
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]));
        }

        filterChain.doFilter(request, response);
    }
}
