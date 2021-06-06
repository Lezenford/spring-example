package com.lezenford.spring.example.context.component;

import org.springframework.stereotype.Component;

@Component
public class Seller {
    public void sell() {
        System.out.println("Sell some items");
    }
}
