package com.lezenford.spring.example.context.component;

import org.springframework.stereotype.Component;

@Component
public class Worker {

    public void work() {
        System.out.println("Work someyhing");
    }
}
