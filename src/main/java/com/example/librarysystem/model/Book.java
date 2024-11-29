package com.example.librarysystem.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable=false)
    @JsonIgnoreProperties("books")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable=false)
    @JsonIgnoreProperties("books")
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, Date publicationDate, Author author, Publisher publisher) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(Long id, String title, Date publicationDate, Author author, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}