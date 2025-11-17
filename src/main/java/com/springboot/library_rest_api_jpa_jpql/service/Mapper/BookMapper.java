package com.springboot.library_rest_api_jpa_jpql.service.Mapper;

import com.springboot.library_rest_api_jpa_jpql.model.Book;
import com.springboot.library_rest_api_jpa_jpql.model.dto.BookReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.BookRespDTO;

public class BookMapper {
    public static Book toEntity(BookReqDTO bookReqDTO) {
        Book book = new Book();

        book.setBookId(bookReqDTO.getBookId());
        book.setTitle(bookReqDTO.getTitle());
        return book;
    }

    public static BookRespDTO toResponseDTO(Book book){
        return new BookRespDTO(book.getBookId(), book.getTitle());
    }
}
