package com.lezenford.spring.example.aop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер. Содержит 2 метода - обычный и бросающий исключение. Предназначен для демонстрации работы Аспектов
 */
@RestController
public class TestController {
    private static final Logger log = LogManager.getLogger(TestController.class);

    @GetMapping
    public String test() {
        log.info("Controller method");
        return "Request success";
    }

    @GetMapping("throw")
    public String testThrow() {
        log.info("Controller method");
        throw new IllegalArgumentException();
    }
}
