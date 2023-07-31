package com.hritik.user.service.services;

import com.hritik.user.service.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    // TODO: update, delete
}
