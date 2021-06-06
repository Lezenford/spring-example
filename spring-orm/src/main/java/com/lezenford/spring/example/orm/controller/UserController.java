package com.lezenford.spring.example.orm.controller;

import com.lezenford.spring.example.orm.model.entity.User;
import com.lezenford.spring.example.orm.model.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Тестовый контроллер со списком пользователей, достающимся из БД
 */
@Controller
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("{id}")
    public String findById(Model model, @PathVariable int id) {
        final User user = userRepository.findById(id);
        if (user == null) {
            return "redirect:/user";
        }
        model.addAttribute("user", user);
        return "user";
    }
}
