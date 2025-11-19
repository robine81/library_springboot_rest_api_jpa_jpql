package com.springboot.library_rest_api_jpa_jpql.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "title"})
public class BookResDTO {
    Long bookId;
    String title;

    public BookResDTO() {}

    public BookResDTO(Long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    @JsonProperty("id")
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
        return "BookRespDTO{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                '}';
    }
}
