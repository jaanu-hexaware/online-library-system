package com.example.librarysystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherTest {

    private Publisher publisher;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("Penguin Books");
        publisher.setBooks(books);
    }

    @Test
    void testSetId() {
        publisher.setId(2L);
        assertEquals(2L, publisher.getId());
    }

    @Test
    void testSetName() {
        publisher.setName("HarperCollins");
        assertEquals("HarperCollins", publisher.getName());
    }

    @Test
    void testSetBooks() {
        List<Book> newBooks = new ArrayList<>();
        publisher.setBooks(newBooks);
        assertEquals(newBooks, publisher.getBooks());
    }

    @Test
    void testOrElseThrowWithPresentPublisher() {
        Optional<Publisher> optionalPublisher = Optional.of(publisher);
        Publisher result = Publisher.orElseThrow(optionalPublisher, () -> new RuntimeException("Publisher not found"));
        assertEquals(publisher, result);
    }

    @Test
    void testOrElseThrowWithEmptyPublisher() {
        Optional<Publisher> optionalPublisher = Optional.empty();
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                Publisher.orElseThrow(optionalPublisher, () -> new RuntimeException("Publisher not found")));
        assertEquals("Publisher not found", exception.getMessage());
    }
}
