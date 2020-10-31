package com.wandisco.sqlidemo.repository;

import com.wandisco.sqlidemo.model.User;

import java.util.List;

public interface UserRepository {

    User getUser(String id);

    List<User> findUsers(String search);

    List<User> getUsers();

    boolean saveUser(User user);
}
