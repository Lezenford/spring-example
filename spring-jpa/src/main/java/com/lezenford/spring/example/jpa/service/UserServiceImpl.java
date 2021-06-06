package com.lezenford.spring.example.jpa.service;

import com.lezenford.spring.example.jpa.model.entity.User;
import com.lezenford.spring.example.jpa.model.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервисного слоя. В не удобно отслеживать открытие транзакций, а так же запрашивать LAZY-поля, пока транзакция открыта
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Инициация LAZY-полей должна выполняться, пока транзакция, где была запрошена сущность, еще не закрыта.
     * В данном случае мы открывает транзакцию до входа в метод с помощью {@link Transactional} и после получения данных из репозитория можем
     * воспользоваться статическим методом Hibernate.initialize() для явной инициализации требуемых LAZY-полей
     */
    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.findById(id).stream().peek(it -> Hibernate.initialize(it.getProducts())).findFirst().orElse(null);
    }

    /**
     * Данный запрос упадет с ошибкой, т.к. транзакция не была явно открыта и запросить LAZY-поля не выйдет
     */
    @Override
    public User findByIdWithoutTransaction(int id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Использование встроенного метода сохранения требует явного открытия транзакции, как и любая другая операция изменения в БД
     */
    @Override
    @Transactional
    public void create(User user) {
        userRepository.save(user);
    }

    /**
     * Данный запрос не сработает - сохранение без явного открытия транзакции невозможно
     */
    @Override
    public void createWithoutTransaction(User user) {
        userRepository.save(user);
    }
}
