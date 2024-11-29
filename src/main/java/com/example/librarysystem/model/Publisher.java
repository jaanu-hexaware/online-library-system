package com.example.librarysystem.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation. JsonManagedReference;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @JsonIgnore
    @ JsonManagedReference private List<Book> books;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Publisher orElseThrow(Optional<Publisher> optionalPublisher, Supplier<? extends RuntimeException> exceptionSupplier) {
        if (optionalPublisher.isPresent()) {
            return optionalPublisher.get();
        } else {
            throw exceptionSupplier.get();
        }
    }
}
