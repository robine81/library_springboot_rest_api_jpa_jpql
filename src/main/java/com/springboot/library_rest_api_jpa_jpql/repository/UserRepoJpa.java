package com.springboot.library_rest_api_jpa_jpql.repository;

import com.springboot.library_rest_api_jpa_jpql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoJpa extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
