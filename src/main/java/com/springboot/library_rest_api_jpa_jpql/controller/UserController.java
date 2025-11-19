package com.springboot.library_rest_api_jpa_jpql.controller;

import com.springboot.library_rest_api_jpa_jpql.model.dto.UserReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserResDTO;
import com.springboot.library_rest_api_jpa_jpql.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<UserResDTO> create(@Valid @RequestBody UserReqDTO userReqDTO) {
        if(service.emailExists(userReqDTO.getEmail())) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(service.create(userReqDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserResDTO>> getAll() { return ResponseEntity.ok(service.getAll()); }
}
