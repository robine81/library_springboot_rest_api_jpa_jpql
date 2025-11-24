package com.springboot.library_rest_api_jpa_jpql.service;

import com.springboot.library_rest_api_jpa_jpql.exception.AccessDeniedException;
import com.springboot.library_rest_api_jpa_jpql.exception.ResourceAlreadyExistsException;
import com.springboot.library_rest_api_jpa_jpql.exception.ResourceNotFoundException;
import com.springboot.library_rest_api_jpa_jpql.model.User;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserResDTO;
import com.springboot.library_rest_api_jpa_jpql.repository.UserRepoJpa;
import com.springboot.library_rest_api_jpa_jpql.service.Mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static com.springboot.library_rest_api_jpa_jpql.service.Mapper.UserMapper.toEntity;
import static com.springboot.library_rest_api_jpa_jpql.service.Mapper.UserMapper.toResponseDTO;

@Service
public class UserService{
    private final UserRepoJpa repo;

    public UserService(UserRepoJpa repo) { this.repo = repo; }

    public List<UserResDTO> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if(!isAdmin)
        {
            throw new AccessDeniedException("Only admins can see all users");
        }
        return repo.findAll().stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public UserResDTO getUserById(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(!isAdmin && !user.getUserName().equals(currentUsername)){
            throw new AccessDeniedException("You can only view your own profile");
        }
        return  toResponseDTO(user);
    }

    public UserResDTO create(UserReqDTO userReqDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if(!isAdmin)
        {
            throw new AccessDeniedException("Only admins can create user");
        }

        if(repo.existsByEmail(userReqDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email " + userReqDTO.getEmail() + "is already registered");
        }

        if (repo.existsByUserName(userReqDTO.getUserName())){
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        return toResponseDTO(repo.save(toEntity(userReqDTO)));
    }

    public boolean delete(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin)
        {
            throw new AccessDeniedException("Only admins can delete user");
        }

        if(repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public UserResDTO updateUser(Long id, UserReqDTO userReqDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin)
        {
            throw new AccessDeniedException("Only admins can update users");
        }
        Optional<User> existing = repo.findById(id);
        if(existing.isPresent()){
            existing.get().setUserName(userReqDTO.getUserName());
            existing.get().setEmail(userReqDTO.getEmail());
            repo.save(existing.get());
            return toResponseDTO(existing.get());
        }
        return null;
    }

    public boolean emailExists(String email) { return repo.existsByEmail(email); }

}
