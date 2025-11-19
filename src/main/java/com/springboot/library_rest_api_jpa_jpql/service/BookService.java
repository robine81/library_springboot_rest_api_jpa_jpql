package com.springboot.library_rest_api_jpa_jpql.service;

import com.springboot.library_rest_api_jpa_jpql.exception.ResourceAlreadyExistsException;
import com.springboot.library_rest_api_jpa_jpql.model.dto.BookReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.BookResDTO;
import com.springboot.library_rest_api_jpa_jpql.repository.BookRepoJpa;
import com.springboot.library_rest_api_jpa_jpql.service.Mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springboot.library_rest_api_jpa_jpql.service.Mapper.BookMapper.toEntity;
import static com.springboot.library_rest_api_jpa_jpql.service.Mapper.BookMapper.toResponseDTO;

@Service
public class BookService {
    private final BookRepoJpa repo;

    public BookService(BookRepoJpa repo) { this.repo = repo; }

    public List<BookResDTO> getAll() {
        return repo.findAll().stream()
                .map(BookMapper::toResponseDTO)
                .toList();
    }

    public BookResDTO create(BookReqDTO bookReqDTO) {
        if(repo.existsByTitle(bookReqDTO.getTitle())) {
            throw new ResourceAlreadyExistsException("Title " + bookReqDTO.getTitle() + "is already registered");
        }
        return toResponseDTO(repo.save(toEntity(bookReqDTO)));
    }

    public boolean titleExists(String title) { return repo.existsByTitle(title); }

}
