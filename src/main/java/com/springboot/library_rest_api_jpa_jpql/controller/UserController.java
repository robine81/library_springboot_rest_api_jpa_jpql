package com.springboot.library_rest_api_jpa_jpql.controller;

import com.springboot.library_rest_api_jpa_jpql.model.dto.UserReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserResDTO;
import com.springboot.library_rest_api_jpa_jpql.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResDTO> create(@Valid @RequestBody UserReqDTO userReqDTO) {
        if(service.emailExists(userReqDTO.getEmail())) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(service.create(userReqDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserResDTO>> getAll() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<UserResDTO> getUser(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@Min(value = 1, message = "ID needs to be non-zero positive integer.") @PathVariable Long id){
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResDTO> updateUser(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO) {
        UserResDTO result = service.updateUser(id, userReqDTO);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
