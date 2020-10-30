package com.wandisco.sqlidemo.controller;

import com.wandisco.sqlidemo.model.User;
import com.wandisco.sqlidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public String getUsersTable(Model model) {
        model.addAttribute("users", repository.getUsers());
        return "allUsers";    }


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Conal")
                .favouriteColour("red")
                .favouriteAnimal("dog")
                .build();
    }

    @GetMapping("/user/form")
    public String getUserForm(Model model) {
        final User exampleUser = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName("PopEye")
                .favouriteColour("Black")
                .favouriteAnimal("Parrot")
                .build();
        model.addAttribute("user", exampleUser);
        return "form";
    }

    @PostMapping("/user")
    public String setUser(@ModelAttribute User user, Model model) {
        repository.saveUser(User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(user.getFirstName())
                .favouriteColour(user.getFavouriteColour())
                .favouriteAnimal(user.getFavouriteAnimal())
                .build());
        model.addAttribute("user", user);
        return "result";
    }
}
