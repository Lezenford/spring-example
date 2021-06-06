package com.lezenford.spring.example.jpa.model.repository;

import com.lezenford.spring.example.jpa.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Пустая реализация {@link JpaRepository} уже содержит набор CRUD операций, которые будут реализованы автоматически при старте приложения
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
