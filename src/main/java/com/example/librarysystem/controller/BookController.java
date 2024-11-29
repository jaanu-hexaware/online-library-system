package com.example.librarysystem.controller;

import com.example.librarysystem.model.Book;
import com.example.librarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/sort/title")
    public List<Book> sortBooksByTitle() {
        return bookService.sortBooksByTitle();
    }

    @GetMapping("/sort/date")
    public List<Book> sortBooksByPublicationDate() {
        return bookService.sortBooksByDate();
    }

    @GetMapping("/report/author/{authorId}")
    public long countBooksByAuthor(@PathVariable Long authorId) {
        return bookService.countBooksByAuthor(authorId);
    }
}