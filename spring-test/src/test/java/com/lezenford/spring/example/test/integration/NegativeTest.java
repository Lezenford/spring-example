package com.lezenford.spring.example.test.integration;

import com.lezenford.spring.example.test.TestApplication;
import com.lezenford.spring.example.test.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

public class NegativeTest extends TestApplication {
    @Test
    public void successFindUserById() {
        final User user = new User();
        user.setId(10);
        user.setName("Ivan");
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        final ResponseEntity<User> exchange = restTemplate.exchange("/users/10", HttpMethod.GET, HttpEntity.EMPTY, User.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(exchange.getBody().getId()).isEqualTo(user.getId());
        Assertions.assertThat(exchange.getBody().getName()).isEqualTo(user.getName());
    }
}
