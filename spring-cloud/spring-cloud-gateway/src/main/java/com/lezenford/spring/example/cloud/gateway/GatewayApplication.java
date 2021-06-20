package com.lezenford.spring.example.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Подключение аннотации {@link EnableDiscoveryClient} говорит приложению, что ему нужно зарегистрироваться в сервисе Eureka Discovery
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
