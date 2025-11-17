package com.springboot.library_rest_api_jpa_jpql.service;

import com.springboot.library_rest_api_jpa_jpql.repository.LoanRepoJpa;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepoJpa repo;

    public LoanService(LoanRepoJpa repo) { this.repo = repo; }
}
