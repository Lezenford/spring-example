package com.lezenford.spring.example.orm.configuration;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация для подключения к проекту Hibernate через Spring-ORM
 */
@Profile("java")
@Configuration
public class JavaHibernateConfiguration {
    @Bean
    public EntityManager entityManager(SessionFactory sessionFactory) {
        return sessionFactory.createEntityManager();
    }

    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("");
        dataSource.setURL("jdbc:h2:./spring-orm");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.lezenford.spring.example.orm.model.entity");
        sessionFactoryBean.setHibernateProperties(properties());
        return sessionFactoryBean;
    }

    private Properties properties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }
}
