package com.springboot.library_rest_api_jpa_jpql.repository;

import com.springboot.library_rest_api_jpa_jpql.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepoJpa extends JpaRepository<Loan, Long> {

}
