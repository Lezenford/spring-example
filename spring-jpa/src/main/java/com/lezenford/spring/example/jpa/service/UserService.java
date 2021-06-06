package com.lezenford.spring.example.jpa.service;

import com.lezenford.spring.example.jpa.model.entity.User;

public interface UserService {

    User findById(int id);

    User findByIdWithoutTransaction(int id);

    void create(User user);

    void createWithoutTransaction(User user);
}
