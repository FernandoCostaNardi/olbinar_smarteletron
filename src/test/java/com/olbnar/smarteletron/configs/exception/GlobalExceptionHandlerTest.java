package com.olbnar.smarteletron.configs.exception;

import com.olbnar.smarteletron.exception.user.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        webRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    void handleUserNotFoundException_returnsNotFoundStatus() {
        UserNotFoundException exception = new UserNotFoundException("User not found");

        ResponseEntity<?> responseEntity = globalExceptionHandler.handleUserNotFoundException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof GlobalExceptionHandler.ErrorDetails);
        GlobalExceptionHandler.ErrorDetails errorDetails = (GlobalExceptionHandler.ErrorDetails) responseEntity.getBody();
        assertEquals("User not found", errorDetails.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, errorDetails.getStatus());
    }

    @Test
    void handleUserNotFoundException_includesRequestDescriptionInErrorDetails() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test/uri");
        webRequest = new ServletWebRequest(request);
        UserNotFoundException exception = new UserNotFoundException("User not found");

        ResponseEntity<?> responseEntity = globalExceptionHandler.handleUserNotFoundException(exception, webRequest);

        GlobalExceptionHandler.ErrorDetails errorDetails = (GlobalExceptionHandler.ErrorDetails) responseEntity.getBody();
        assertNotNull(errorDetails.getDetails());
        assertTrue(errorDetails.getDetails().contains("/test/uri"));
    }

    @Test
    void handleAccessDeniedException_returnsForbiddenStatus() {
        AccessDeniedException exception = new AccessDeniedException("Access is denied");

        ResponseEntity<?> responseEntity = globalExceptionHandler.handleAccessDeniedException(exception, webRequest);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof GlobalExceptionHandler.ErrorDetails);
        GlobalExceptionHandler.ErrorDetails errorDetails = (GlobalExceptionHandler.ErrorDetails) responseEntity.getBody();
        assertEquals("Acesso negado: Você não tem permissão para acessar este recurso", errorDetails.getMessage());
        assertEquals(HttpStatus.FORBIDDEN, errorDetails.getStatus());
    }

    @Test
    void handleAccessDeniedException_includesRequestDescriptionInErrorDetails() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/forbidden/uri");
        webRequest = new ServletWebRequest(request);
        AccessDeniedException exception = new AccessDeniedException("Access is denied");

        ResponseEntity<?> responseEntity = globalExceptionHandler.handleAccessDeniedException(exception, webRequest);

        GlobalExceptionHandler.ErrorDetails errorDetails = (GlobalExceptionHandler.ErrorDetails) responseEntity.getBody();
        assertTrue(errorDetails.getDetails().contains("/forbidden/uri"));
    }
}