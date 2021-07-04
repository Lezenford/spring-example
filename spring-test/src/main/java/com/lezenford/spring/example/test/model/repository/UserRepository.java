package com.lezenford.spring.example.test.model.repository;

import com.lezenford.spring.example.test.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
