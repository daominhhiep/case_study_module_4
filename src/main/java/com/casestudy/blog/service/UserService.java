package com.casestudy.blog.service;


import com.casestudy.blog.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

}
