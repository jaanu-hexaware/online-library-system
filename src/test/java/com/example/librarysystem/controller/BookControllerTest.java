package com.example.librarysystem.controller;

import com.example.librarysystem.model.Book;
import com.example.librarysystem.service.BookService;
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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
    }

    @Test
    void createBook() throws Exception {
        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Book Title\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book Title"));

        verify(bookService, times(1)).saveBook(any(Book.class));
    }

    @Test
    void getAllBooks() throws Exception {
        List<Book> books = Arrays.asList(book);
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(books.size()))
                .andExpect(jsonPath("$[0].title").value("Book Title"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book Title"));

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void updateBook() throws Exception {
        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Updated Book Title\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book Title"));

        verify(bookService, times(1)).saveBook(any(Book.class));
    }

    @Test
    void deleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    void sortBooksByTitle() throws Exception {
        List<Book> books = Arrays.asList(book);
        when(bookService.sortBooksByTitle()).thenReturn(books);

        mockMvc.perform(get("/books/sort/title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(books.size()))
                .andExpect(jsonPath("$[0].title").value("Book Title"));

        verify(bookService, times(1)).sortBooksByTitle();
    }

    @Test
    void sortBooksByDate() throws Exception {
        List<Book> books = Arrays.asList(book);
        when(bookService.sortBooksByDate()).thenReturn(books);

        mockMvc.perform(get("/books/sort/date"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(books.size()))
                .andExpect(jsonPath("$[0].title").value("Book Title"));

        verify(bookService, times(1)).sortBooksByDate();
    }

    @Test
    void countBooksByAuthor() throws Exception {
        when(bookService.countBooksByAuthor(1L)).thenReturn(5L);

        mockMvc.perform(get("/books/report/author/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(bookService, times(1)).countBooksByAuthor(1L);
    }
}
