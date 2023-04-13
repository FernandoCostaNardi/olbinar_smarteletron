package com.olbnar.smarteletron.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olbnar.smarteletron.dto.CredentialsDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private final UserAuthenticationProvider provider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, //
                                    HttpServletResponse response, //
                                    FilterChain filterChain) throws ServletException, IOException { //
        if("signIn".equals(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod())){
            CredentialsDto credentialsDto = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);

            try {
                SecurityContextHolder.getContext().setAuthentication(
                        provider.validateCredentials(credentialsDto)
                );
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }

        filterChain.doFilter(request, response);
    }
}
