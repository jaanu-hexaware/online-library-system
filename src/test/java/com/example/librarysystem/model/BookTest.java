package com.example.librarysystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;
    private Author author;
    private Publisher publisher;
    private Date publicationDate;

    @BeforeEach
    void setUp() {
        author = new Author("John Doe");
        publisher = new Publisher();
        publicationDate = new Date();
        book = new Book(1L, "Sample Title", publicationDate, author, publisher);
    }

    @Test
    void testConstructor() {
        Book newBook = new Book("New Title", publicationDate, author, publisher);
        assertNotNull(newBook);
        assertEquals("New Title", newBook.getTitle());
        assertEquals(publicationDate, newBook.getPublicationDate());
        assertEquals(author, newBook.getAuthor());
        assertEquals(publisher, newBook.getPublisher());
    }

    @Test
    void testFullConstructor() {
        assertNotNull(book);
        assertEquals(1L, book.getId());
        assertEquals("Sample Title", book.getTitle());
        assertEquals(publicationDate, book.getPublicationDate());
        assertEquals(author, book.getAuthor());
        assertEquals(publisher, book.getPublisher());
    }

    @Test
    void testSetId() {
        book.setId(2L);
        assertEquals(2L, book.getId());
    }

    @Test
    void testSetTitle() {
        book.setTitle("Updated Title");
        assertEquals("Updated Title", book.getTitle());
    }

    @Test
    void testSetPublicationDate() {
        Date newDate = new Date();
        book.setPublicationDate(newDate);
        assertEquals(newDate, book.getPublicationDate());
    }

    @Test
    void testSetAuthor() {
        Author newAuthor = new Author("Jane Doe");
        book.setAuthor(newAuthor);
        assertEquals(newAuthor, book.getAuthor());
    }

    @Test
    void testSetPublisher() {
        Publisher newPublisher = new Publisher();
        book.setPublisher(newPublisher);
        assertEquals(newPublisher, book.getPublisher());
    }
}
