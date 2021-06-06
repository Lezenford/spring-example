package com.lezenford.spring.example.mvc.component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сущность, содержащая данные о продукте
 */
public class Product {
    private int id;
    private String name;
    private int cost;

    //Счетчик для генерации ID для новых продуктов
    private static final AtomicInteger COUNTER = new AtomicInteger();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        if (id == 0) {
            id = COUNTER.incrementAndGet();
        }
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
