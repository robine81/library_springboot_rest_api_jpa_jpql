package com.springboot.library_rest_api_jpa_jpql.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookReqDTO {
    private Long bookId;
    @NotBlank(message = "Title must be specified")
    @Size(min = 1, max = 200, message = "Title but be between 2 and 200 characters")
    private String title;

    public BookReqDTO() {}

    public BookReqDTO(Long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookReqDTO{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                '}';
    }
}
