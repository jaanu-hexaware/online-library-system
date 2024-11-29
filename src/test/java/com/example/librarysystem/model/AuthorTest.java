package com.example.librarysystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {

    private Author author;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        author = new Author(1L, "John Doe", books);
    }

    @Test
    void testConstructor() {
        Author newAuthor = new Author("Jane Doe");
        assertNotNull(newAuthor);
        assertEquals("Jane Doe", newAuthor.getName());
    }

    @Test
    void testFullConstructor() {
        assertNotNull(author);
        assertEquals(1L, author.getId());
        assertEquals("John Doe", author.getName());
        assertEquals(books, author.getBooks());
    }

    @Test
    void testSetId() {
        author.setId(2L);
        assertEquals(2L, author.getId());
    }

    @Test
    void testSetName() {
        author.setName("Alice Smith");
        assertEquals("Alice Smith", author.getName());
    }

    @Test
    void testSetBooks() {
        List<Book> newBooks = new ArrayList<>();
        author.setBooks(newBooks);
        assertEquals(newBooks, author.getBooks());
    }

    @Test
    void testOrElseThrowWithPresentAuthor() {
        Optional<Author> optionalAuthor = Optional.of(author);
        Author result = Author.orElseThrow(optionalAuthor, new RuntimeException("Author not found"));
        assertEquals(author, result);
    }

    @Test
    void testOrElseThrowWithEmptyOptional() {
        Optional<Author> optionalAuthor = Optional.empty();
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                Author.orElseThrow(optionalAuthor, new RuntimeException("Author not found")));
        assertEquals("Author not found", exception.getMessage());
    }
}
