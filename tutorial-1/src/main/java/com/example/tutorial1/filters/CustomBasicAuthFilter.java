package com.example.tutorial1.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Service
public class CustomBasicAuthFilter extends OncePerRequestFilter {
    private static int BASIC_LENGTH = 6;
    private static String EXAMPLE_USERNAME = "user";
    private static String EXAMPLE_PASSWORD = "12345";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }
        var basicToken = headerAuthorization.substring(BASIC_LENGTH);
        byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);

        // user: 12345
        String basicAuthValue = new String(basicTokenDecoded);

        // ["user", "123456"]
        String[] basicAuthSplit = basicAuthValue.split(":");

        if (basicAuthSplit[0].equals(EXAMPLE_USERNAME) &&
                basicAuthSplit[1].equals(EXAMPLE_PASSWORD)) {
            var authToken = new UsernamePasswordAuthenticationToken(
                    basicAuthSplit[0], null, null
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}






















