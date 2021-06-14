package com.lezenford.spring.example.orm;

import com.lezenford.spring.example.orm.model.entity.Comment;
import com.lezenford.spring.example.orm.model.entity.User;
import com.lezenford.spring.example.orm.model.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public HibernateApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final User user = userRepository.findById(1);
        System.out.println("");
        final List<Comment> comments = user.getComments();
        System.out.println("");
    }
}
