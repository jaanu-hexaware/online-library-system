package com.example.librarysystem.service;

import com.example.librarysystem.exception.ResourceNotFoundException;
import com.example.librarysystem.model.Author;
import com.example.librarysystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
    }

    public void deleteAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
    }
}
