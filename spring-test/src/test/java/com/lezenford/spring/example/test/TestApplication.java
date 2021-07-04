package com.lezenford.spring.example.test;

import com.lezenford.spring.example.test.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.jpa.show-sql=false"})
@AutoConfigureMockMvc
public class TestApplication {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        Mockito.reset(userRepository);
    }

}
