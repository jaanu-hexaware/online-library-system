package com.example.librarysystem.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation. JsonManagedReference;
import java.util.List;
import java.util.Optional;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    @ JsonManagedReference    private List<Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static Author orElseThrow(Optional<Author> optionalAuthor, RuntimeException exception) {
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            throw exception;
        }
    }
}
