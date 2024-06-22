package com.prueba.nisum.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;


public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void setUp() {
        customExceptionHandler = new CustomExceptionHandler();
    }


    @Test
    public void handleHttpMessageNotReadableExceptionTest() {
        HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);
        ResponseEntity<Map<String, String>> response = customExceptionHandler.handleHttpMessageNotReadableException();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid JSON format", response.getBody().get("mensaje"));
    }

    @Test
    public void handleResourceNotFoundExceptionTest() {
        ResourceNotFoundException ex = new ResourceNotFoundException("User not found");
        ResponseEntity<Map<String, String>> response = customExceptionHandler.handleResourceNotFoundException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User not found", response.getBody().get("mensaje"));
    }

    @Test
    public void handleAllExceptionsTest() {
        Exception ex = new Exception("Generic error");
        ResponseEntity<Map<String, String>> response = customExceptionHandler.handleAllExceptions(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Generic error", response.getBody().get("mensaje"));
    }

    @Test
    public void handleEmailAlreadyExistsExceptionTest() {
        EmailAlreadyExistsException ex = new EmailAlreadyExistsException("Email already exists");
        ResponseEntity<Map<String, String>> response = customExceptionHandler.handleEmailAlreadyExistsException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Email already exists", response.getBody().get("mensaje"));
    }
}
