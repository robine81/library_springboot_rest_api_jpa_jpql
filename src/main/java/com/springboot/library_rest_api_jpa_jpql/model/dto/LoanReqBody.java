package com.springboot.library_rest_api_jpa_jpql.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanReqBody {
    private Long loanId;
    @NotNull(message = "User ID is required")
    @Min(value = 1, message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "Book ID is required")
    @Min(value = 1, message = "Book ID must be a positive number")
    private Long bookId;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "LoanReqBody{" +
                "loanId=" + loanId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
