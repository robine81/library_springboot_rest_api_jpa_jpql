package com.springboot.library_rest_api_jpa_jpql.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name= "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate loanDate;
    private Local returnDate;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    public Loan() {}

    public Loan(Long id, String name, LocalDate loanDate, Local returnDate, User user, Book book) {
        this.id = id;
        this.name = name;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
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

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public Local getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Local returnDate) {
        this.returnDate = returnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
