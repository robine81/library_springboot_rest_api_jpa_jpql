package com.springboot.library_rest_api_jpa_jpql.service.Mapper;

import com.springboot.library_rest_api_jpa_jpql.model.User;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserReqDTO;
import com.springboot.library_rest_api_jpa_jpql.model.dto.UserResDTO;

public class UserMapper {
    public static User toEntity(UserReqDTO userReqDTO){
        User user = new User();

        user.setUserId(userReqDTO.getUserId());
        user.setUserName(userReqDTO.getUserName());
        user.setEmail(userReqDTO.getEmail());
        return user;
    }

    public static UserResDTO toResponseDTO(User user) {
        return new UserResDTO(user.getUserId(), user.getUserName(), user.getEmail());
    }
}
