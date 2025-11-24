package com.springboot.library_rest_api_jpa_jpql.service;

import com.springboot.library_rest_api_jpa_jpql.exception.AccessDeniedException;
import com.springboot.library_rest_api_jpa_jpql.exception.BadRequestException;
import com.springboot.library_rest_api_jpa_jpql.exception.ResourceNotFoundException;
import com.springboot.library_rest_api_jpa_jpql.model.Book;
import com.springboot.library_rest_api_jpa_jpql.model.Loan;
import com.springboot.library_rest_api_jpa_jpql.model.User;
import com.springboot.library_rest_api_jpa_jpql.model.dto.LoanResDTO;
import com.springboot.library_rest_api_jpa_jpql.repository.BookRepoJpa;
import com.springboot.library_rest_api_jpa_jpql.repository.LoanRepoJpa;
import com.springboot.library_rest_api_jpa_jpql.repository.UserRepoJpa;
import com.springboot.library_rest_api_jpa_jpql.service.Mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepoJpa loanRepo;

    @Autowired
    private UserRepoJpa userRepo;

    @Autowired
    private BookRepoJpa bookRepo;

    public List<LoanResDTO> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        String currentUsername = authentication.getName();

        if(!isAdmin)
        {
            throw new AccessDeniedException("Only admins can see all loans");
        }
        if (isAdmin()) {
            return loanRepo.findAll().stream()
                    .map(LoanMapper::toResponseDTO)
                    .toList();
        } else {
            return loanRepo.findByUser_UserName(currentUsername).stream()
                    .map(LoanMapper::toResponseDTO)
                    .toList();
        }
    }

    public LoanResDTO getLoanById(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (!isAdmin()) {
            String currentUsername = getCurrentUsername();
            if (!loan.getUser().getUserName().equals(currentUsername)) {
                throw new AccessDeniedException("You can only access your own loans");
            }
        }

        return convertToResDTO(loan);
    }

    public LoanResDTO borrowBook(Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        User user = userRepo.findByUserName(currentUsername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        List<Loan> activeLoans = loanRepo.findByBook_BookIdAndReturnDateIsNull(bookId);
        if (!activeLoans.isEmpty()) {
            throw new BadRequestException("This book is already on loan");
        }

        List<Loan> userActiveLoans = loanRepo.findByUser_UserIdAndBook_BookIdAndReturnDateIsNull(
                user.getUserId(), bookId);
        if (!userActiveLoans.isEmpty()) {
            throw new BadRequestException("You have already borrowed this book");
        }

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setStartDate(LocalDate.now());
        loan.setReturnDate(null);

        Loan savedLoan = loanRepo.save(loan);
        return convertToResDTO(savedLoan);
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private LoanResDTO convertToResDTO(Loan loan) {
        return new LoanResDTO(
                loan.getLoanId(),
                loan.getStartDate(),
                loan.getReturnDate(),
                loan.getUser().getUserId(),
                loan.getUser().getUserName(),
                loan.getBook().getBookId(),
                loan.getBook().getTitle()
        );
    }
}