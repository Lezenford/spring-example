package com.lezenford.spring.example.orm.model.repository;

import com.lezenford.spring.example.orm.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Реализация репозитория, работающего с сущностью User
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Поиск всех пользователей в БД с помощью нативного запроса
     */
    @Override
    public List<User> findAll() {
        return entityManager.createNativeQuery("select * from USERS", User.class).getResultList();
    }

    /**
     * Поиск конкретного пользователя в БД по ID с помощью CRUD операция entityManager
     */
    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }
}
