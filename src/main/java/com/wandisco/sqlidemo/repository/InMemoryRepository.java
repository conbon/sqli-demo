package com.wandisco.sqlidemo.repository;

import com.wandisco.sqlidemo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InMemoryRepository implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public List<User> findUsers(String search) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public boolean saveUser(User user) {
        return users.add(user);
    }
}
