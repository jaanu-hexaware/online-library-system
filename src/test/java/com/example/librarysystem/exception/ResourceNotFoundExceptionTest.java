package com.example.librarysystem.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This is a specific exception message";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
