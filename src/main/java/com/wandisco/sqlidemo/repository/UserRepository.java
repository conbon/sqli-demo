package com.wandisco.sqlidemo.repository;

import com.wandisco.sqlidemo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public boolean saveUser(User user) {
        return users.add(user);
    }
}
