package com.springboot.library_rest_api_jpa_jpql.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String name;

    @OneToMany(mappedBy = "loans", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public Book() {}

    public Book(Long bookId, String name, List<Loan> loans) {
        this.bookId = bookId;
        this.name = name;
        this.loans = loans;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", loans=" + loans +
                '}';
    }
}
