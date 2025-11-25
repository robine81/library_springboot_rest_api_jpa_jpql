package com.springboot.library_rest_api_jpa_jpql.controller;

import com.springboot.library_rest_api_jpa_jpql.model.dto.LoanReqBody;
import com.springboot.library_rest_api_jpa_jpql.model.dto.LoanResDTO;
import com.springboot.library_rest_api_jpa_jpql.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<LoanResDTO>> getAll() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("my-loans")
    public ResponseEntity<List<LoanResDTO>> getMyLoans() {
        return ResponseEntity.ok(service.getMyLoans());
    }

    @GetMapping("/active")
    public ResponseEntity<List<LoanResDTO>> getActiveLoans() {
        return ResponseEntity.ok(service.getActiveLoans());
    }

    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<LoanResDTO> borrowBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(service.borrowBook(bookId));
    }

    @PostMapping
    public ResponseEntity<LoanResDTO> borrowBookForUser(@Valid @RequestBody LoanReqBody loanReqBody) {
        return ResponseEntity.ok(service.borrowBookForUser(loanReqBody.getUserId(), loanReqBody.getBookId()
        ));
    }

    @PutMapping("/{bookId}/return")
    public ResponseEntity<LoanResDTO> returnBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(service.returnBook(bookId));
    }
}
