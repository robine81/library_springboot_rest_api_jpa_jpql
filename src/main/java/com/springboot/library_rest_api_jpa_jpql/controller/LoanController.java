package com.springboot.library_rest_api_jpa_jpql.controller;

import com.springboot.library_rest_api_jpa_jpql.model.dto.LoanResDTO;
import com.springboot.library_rest_api_jpa_jpql.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) { this.service = service; }

    // Hämta mina lån
    // USER ser sina egna, ADMIN ser alla
    @GetMapping
    public ResponseEntity<List<LoanResDTO>> getAll() { return ResponseEntity.ok(service.getAll()); }

    @PostMapping("/{bookId}")
    public ResponseEntity<LoanResDTO> borrowBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(service.borrowBook(bookId));
    }

    // Admin kan skapa lån för någon annan
    /*@PostMapping("/admin")
    public ResponseEntity<LoanResDTO> createLoanAsAdmin(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        // Endast ADMIN kan göra detta
    }*/

    // Returnera en bok
    /*@PutMapping("/{loanId}/return")
    public ResponseEntity<LoanResDTO> returnBook(@PathVariable Long loanId) {
        // Sätt returnDate till idag
    }*/
}
