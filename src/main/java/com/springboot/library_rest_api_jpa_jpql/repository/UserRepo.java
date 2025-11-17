package com.springboot.library_rest_api_jpa_jpql.repository;

import com.springboot.library_rest_api_jpa_jpql.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private List<User> users = new ArrayList<>();

    public UserRepo() {
        users.add(new User(1L, "Anna Annsson", "anna.annsson@mail.com"));
        users.add(new User(2L, "Björn Björnsson", "bjorn.bjornsson@mail.com"));
        users.add(new User(3L, "Carl Carlsson", "carl.carlsson@mail.com"));
        users.add(new User(4L, "David Davidsson", "david.davidsson@mail.com"));
    }
}
