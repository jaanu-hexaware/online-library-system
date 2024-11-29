package com.example.librarysystem.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "This is a general exception message";
        GeneralException exception = new GeneralException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
