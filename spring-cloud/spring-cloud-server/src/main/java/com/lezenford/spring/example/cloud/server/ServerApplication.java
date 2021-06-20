package com.lezenford.spring.example.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Включение аннотации {@link EnableConfigServer} превращает spring-service в полноценный сервер конфигураций. В текущем виде он берет данные из
 * папки resources/config
 */
@EnableConfigServer
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
