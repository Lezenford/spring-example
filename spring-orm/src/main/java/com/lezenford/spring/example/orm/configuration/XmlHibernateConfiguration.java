package com.lezenford.spring.example.orm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Конфигурация для подключения к проекту Hibernate через XML-конфигурацию
 */
@Profile("xml")
@Configuration
public class XmlHibernateConfiguration {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }
}
