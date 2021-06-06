package com.lezenford.spring.example.orm.model.repository;

import com.lezenford.spring.example.orm.model.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int id);
}
