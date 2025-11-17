package com.springboot.library_rest_api_jpa_jpql.repository;

import com.springboot.library_rest_api_jpa_jpql.model.Book;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepo {
    private List<Book> books = new ArrayList<>();

    public BookRepo() {
        books.add(new Book(1L, "Harry Potter"));
        books.add(new Book(2L, "Lord of the Rings"));
        books.add(new Book(3L, "Dune"));
        books.add(new Book(4L, "Avatar"));
    }
}
