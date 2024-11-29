package com.example.librarysystem.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidInputExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This is a specific exception message";
        InvalidInputException exception = new InvalidInputException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
