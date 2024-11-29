package com.example.librarysystem.controller;

import com.example.librarysystem.model.Author;
import com.example.librarysystem.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Author Name");
    }

    @Test
    void createAuthor() throws Exception {
        when(authorService.saveAuthor(any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Author Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Author Name"));

        verify(authorService, times(1)).saveAuthor(any(Author.class));
    }

    @Test
    void getAllAuthors() throws Exception {
        List<Author> authors = Arrays.asList(author);
        when(authorService.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(authors.size()))
                .andExpect(jsonPath("$[0].name").value("Author Name"));

        verify(authorService, times(1)).getAllAuthors();
    }

    @Test
    void getAuthorById() throws Exception {
        when(authorService.getAuthorById(1L)).thenReturn(author);

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Author Name"));

        verify(authorService, times(1)).getAuthorById(1L);
    }

    @Test
    void updateAuthor() throws Exception {
        when(authorService.saveAuthor(any(Author.class))).thenReturn(author);

        mockMvc.perform(put("/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Author Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Author Name"));

        verify(authorService, times(1)).saveAuthor(any(Author.class));
    }

    @Test
    void deleteAuthor() throws Exception {
        doNothing().when(authorService).deleteAuthor(1L);

        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().isOk());

        verify(authorService, times(1)).deleteAuthor(1L);
    }
}
