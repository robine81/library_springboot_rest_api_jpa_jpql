package com.springboot.library_rest_api_jpa_jpql.model.dto;

import java.time.LocalDate;

public class LoanResDTO {

    private Long loanId;
    private LocalDate startDate;
    private LocalDate returnDate;

    private Long userId;
    private String username;

    private Long bookId;
    private String bookTitle;

    public LoanResDTO() {}

    public LoanResDTO(Long loanId, LocalDate startDate, LocalDate returnDate,
                      Long userId, String username, Long bookId, String bookTitle) {
        this.loanId = loanId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return "LoanResDTO{" +
                "loanId=" + loanId +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                '}';
    }
}