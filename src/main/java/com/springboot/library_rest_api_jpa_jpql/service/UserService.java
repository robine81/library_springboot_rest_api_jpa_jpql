package com.springboot.library_rest_api_jpa_jpql.service;

import com.springboot.library_rest_api_jpa_jpql.exception.ResourceAlreadyExistsException;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserRespDTO;
import com.springboot.library_rest_api_jpa_jpql.repository.UserRepoJpa;
import com.springboot.library_rest_api_jpa_jpql.service.Mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.springboot.library_rest_api_jpa_jpql.service.Mapper.UserMapper.toEntity;
import static com.springboot.library_rest_api_jpa_jpql.service.Mapper.UserMapper.toResponseDTO;

@Service
public class UserService{
    private final UserRepoJpa repo;

    public UserService(UserRepoJpa repo) { this.repo = repo; }

    public List<UserRespDTO> getAll() {
        return repo.findAll().stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public UserRespDTO create(UserReqDTO userReqDTO) {
        if(repo.existsByEmail(userReqDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email " + userReqDTO.getEmail() + "is already registered");
        }
        return toResponseDTO(repo.save(toEntity(userReqDTO)));
    }

    public boolean emailExists(String email) { return repo.existsByEmail(email); }

}
