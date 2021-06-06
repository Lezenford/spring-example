package com.lezenford.spring.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Добавление аннотации {@link EnableAspectJAutoProxy} необходимо для работы Spring AOP
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class AspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectApplication.class, args);
    }
}
