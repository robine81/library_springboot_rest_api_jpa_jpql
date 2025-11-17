package com.springboot.library_rest_api_jpa_jpql.repository;

import com.springboot.library_rest_api_jpa_jpql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepoJpa extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
}
