package com.lezenford.spring.example.cloud.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class TestController {

    //Данное значение тянется из конфигурации, лежащей на сервере конфигураций
    @Value("${application.message}")
    private String value;

    @GetMapping
    public String test() {
        return value;
    }
}
