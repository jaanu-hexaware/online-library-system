package com.example.librarysystem.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorDetailsTest {

    private ErrorDetails errorDetails;
    private Date timestamp;
    private String message;
    private String details;

    @BeforeEach
    void setUp() {
        timestamp = new Date();
        message = "Error message";
        details = "Error details";
        errorDetails = new ErrorDetails(timestamp, message, details);
    }

    @Test
    void testConstructor() {
        assertNotNull(errorDetails);
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    void testSetTimestamp() {
        Date newTimestamp = new Date();
        errorDetails.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, errorDetails.getTimestamp());
    }

    @Test
    void testSetMessage() {
        String newMessage = "New error message";
        errorDetails.setMessage(newMessage);
        assertEquals(newMessage, errorDetails.getMessage());
    }

    @Test
    void testSetDetails() {
        String newDetails = "New error details";
        errorDetails.setDetails(newDetails);
        assertEquals(newDetails, errorDetails.getDetails());
    }
}
