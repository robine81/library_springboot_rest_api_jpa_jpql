package com.springboot.library_rest_api_jpa_jpql.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;

    @OneToMany(mappedBy = "loans", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public User() {}

    public User(Long userId, String name, List<Loan> loans) {
        this.userId = userId;
        this.name = name;
        this.loans = loans;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", loans=" + loans +
                '}';
    }
}
