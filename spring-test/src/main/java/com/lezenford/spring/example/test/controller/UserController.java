package com.lezenford.spring.example.test.controller;

import com.lezenford.spring.example.test.model.entity.User;
import com.lezenford.spring.example.test.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
