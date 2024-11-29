package com.example.librarysystem.service;

import com.example.librarysystem.exception.ResourceNotFoundException;
import com.example.librarysystem.model.Book;
import com.example.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    public void deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }
    
    public List<Book> sortBooksByTitle() {
        return bookRepository.findAll().stream()
                .sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
                .collect(Collectors.toList());
    }

    public List<Book> sortBooksByDate() {
        return bookRepository.findAll().stream()
                .sorted((b1, b2) -> b1.getPublicationDate().compareTo(b2.getPublicationDate()))
                .collect(Collectors.toList());
    }

    public long countBooksByAuthor(Long authorId) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().getId().equals(authorId))
                .count();
    }
}
