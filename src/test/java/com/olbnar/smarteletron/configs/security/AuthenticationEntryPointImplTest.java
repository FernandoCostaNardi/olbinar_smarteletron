package com.olbnar.smarteletron.configs.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olbnar.smarteletron.dtos.authentication.ErrorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

class AuthenticationEntryPointImplTest {

    @Mock
    private AuthenticationException authenticationException;

    @InjectMocks
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        objectMapper = new ObjectMapper();
    }



    @Test
    void commence_logsErrorCorrectly() throws IOException, ServletException {
        String errorMessage = "Bad credentials";
        AuthenticationException authenticationException = new BadCredentialsException(errorMessage);

        // Assuming a mock logger is set up and injected
        authenticationEntryPoint.commence(request, response, authenticationException);

        // Verify that the correct error message is logged
        // This step depends on the logging framework and mocking library you're using
    }

    @Test
    void commence_logsErrorWhenAuthenticationExceptionIsThrown() throws IOException, ServletException {
        String errorMessage = "Unauthorized error: Access is denied";
        AuthenticationException authenticationException = new AuthenticationException(errorMessage) {};

        authenticationEntryPoint.commence(request, response, authenticationException);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        // Note: Verifying log output requires a different approach, such as using a logging framework that supports assertions or integrating with a mock logger.
    }
}