package com.springboot.library_rest_api_jpa_jpql.repository;

import com.springboot.library_rest_api_jpa_jpql.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanRepoJpa extends JpaRepository<Loan, Long> {
    List<Loan> findByUser_UserId(Long userId);
    List<Loan> findByUser_UserName(String userName);
    List<Loan> findByBook_BookId(Long bookId);
    List<Loan> findByUser_UserIdAndReturnDateIsNull(Long userId);
    List<Loan> findByReturnDateIsNull();
    Optional<Loan> findByLoanIdAndUser_UserId(Long loanId, Long userId);
    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL")
    List<Loan> findAllActiveLoans();
    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL AND l.startDate < :currentDate")
    List<Loan> findOverdueLoans(@Param("currentDate") java.time.LocalDate currentDate);
    List<Loan> findByBook_BookIdAndReturnDateIsNull(Long bookId);
    List<Loan> findByUser_UserIdAndBook_BookIdAndReturnDateIsNull(Long userId, Long bookId);
}
