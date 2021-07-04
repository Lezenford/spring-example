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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PositiveTest extends TestApplication {
    @Test
    public void successFindAllUsers() {
        final User user = new User();
        user.setId(1);
        user.setName("Ivan");
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        final ResponseEntity<User[]> exchange = restTemplate.exchange("/users", HttpMethod.GET, HttpEntity.EMPTY, User[].class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(exchange.getBody()).isNotEmpty();
        Assertions.assertThat(exchange.getBody().length).isEqualTo(1);
    }

    @Test
    public void webMvcTest() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }
}
