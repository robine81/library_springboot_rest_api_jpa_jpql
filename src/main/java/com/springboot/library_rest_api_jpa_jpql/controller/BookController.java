package com.springboot.library_rest_api_jpa_jpql.controller;

import com.springboot.library_rest_api_jpa_jpql.model.dto.BookReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.BookRespDTO;
import com.springboot.library_rest_api_jpa_jpql.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.RectangularShape;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<BookRespDTO> create(@Valid @RequestBody BookReqDTO bookReqDTO)
    {
        if(service.titleExists(bookReqDTO.getTitle())) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(service.create(bookReqDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookRespDTO>> getAll() { return ResponseEntity.ok(service.getAll()); }
}
