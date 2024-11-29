package com.example.librarysystem.service;

import com.example.librarysystem.exception.ResourceNotFoundException;
import com.example.librarysystem.model.Author;
import com.example.librarysystem.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        author = new Author();
        author.setId(1L);
        author.setName("Author Name");
    }

    @Test
    void saveAuthor() {
        when(authorRepository.save(author)).thenReturn(author);

        Author savedAuthor = authorService.saveAuthor(author);

        assertNotNull(savedAuthor);
        assertEquals(author.getName(), savedAuthor.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void getAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));

        List<Author> authors = authorService.getAllAuthors();

        assertNotNull(authors);
        assertEquals(1, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void getAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author foundAuthor = authorService.getAuthorById(1L);

        assertNotNull(foundAuthor);
        assertEquals(author.getName(), foundAuthor.getName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void getAuthorById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            authorService.getAuthorById(1L);
        });

        assertEquals("Author not found with id: 1", exception.getMessage());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void deleteAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        doNothing().when(authorRepository).deleteById(1L);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAuthor_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            authorService.deleteAuthor(1L);
        });

        assertEquals("Author not found with id: 1", exception.getMessage());
        verify(authorRepository, times(1)).findById(1L);
    }
}
