package com.wandisco.sqlidemo.repository;

import com.wandisco.sqlidemo.model.User;
import com.wandisco.sqlidemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InMemoryRepository implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public User getUser(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(User.builder().build());
    }

    @Override
    public List<User> findUsers(String search) {
        return users.stream()
                .filter(u -> u.getFirstName().contains(search))
                .collect(Collectors.toList());
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
