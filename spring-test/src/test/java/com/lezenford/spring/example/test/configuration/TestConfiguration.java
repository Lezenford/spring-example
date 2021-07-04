package com.lezenford.spring.example.test.configuration;

import com.lezenford.spring.example.test.model.repository.UserRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public UserRepository userRepositoryTest() {
        return Mockito.mock(UserRepository.class);
    }
}
