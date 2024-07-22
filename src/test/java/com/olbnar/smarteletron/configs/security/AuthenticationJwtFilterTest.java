package com.olbnar.smarteletron.configs.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthenticationJwtFilterTest {

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private AuthenticationJwtFilter authenticationJwtFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void successfulAuthenticationWithJwtToken() throws ServletException, IOException {
        String jwt = "valid.jwt.token";
        request.addHeader("Authorization", "Bearer " + jwt);
        when(jwtProvider.validateJwt(jwt)).thenReturn(true);
        when(jwtProvider.getSubjectJwt(jwt)).thenReturn("1");
        UserDetails userDetails = new User("user", "password", new ArrayList<>());
        when(userDetailsService.loadUserById(1L)).thenReturn(userDetails);

        authenticationJwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(jwtProvider, times(1)).validateJwt(jwt);
        verify(userDetailsService, times(1)).loadUserById(1L);
    }

    @Test
    void authenticationFailsWithInvalidJwtToken() throws ServletException, IOException {
        String jwt = "invalid.jwt.token";
        request.addHeader("Authorization", "Bearer " + jwt);
        when(jwtProvider.validateJwt(jwt)).thenReturn(false);

        authenticationJwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(jwtProvider, times(1)).validateJwt(jwt);
        verify(userDetailsService, never()).loadUserById(anyLong());
    }

    @Test
    void authenticationSkippedWhenNoAuthorizationHeaderPresent() throws ServletException, IOException {
        authenticationJwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(jwtProvider, never()).validateJwt(anyString());
        verify(userDetailsService, never()).loadUserById(anyLong());
    }

    @Test
    void exceptionDuringAuthenticationDoesNotStopFilterChain() throws ServletException, IOException {
        String jwt = "exception.jwt.token";
        request.addHeader("Authorization", "Bearer " + jwt);
        when(jwtProvider.validateJwt(jwt)).thenThrow(RuntimeException.class);

        authenticationJwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }
}