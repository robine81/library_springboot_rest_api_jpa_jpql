package com.springboot.library_rest_api_jpa_jpql.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public Book() {}

    public Book(Long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public Book(Long bookId, String title, List<Loan> loans) {
        this.bookId = bookId;
        this.title = title;
        this.loans = loans;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + title + '\'' +
                ", loans=" + loans +
                '}';
    }
}
