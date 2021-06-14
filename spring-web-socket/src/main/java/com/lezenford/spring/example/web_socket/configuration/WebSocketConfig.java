package com.lezenford.spring.example.web_socket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Конфигурация WebSocket
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * Имя сокета (аналог TCP-сокета)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket").withSockJS();
    }

    /**
     * Настройка брокера сообщений
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // префикс отправителя
        config.enableSimpleBroker("/topic");
        // префикс получателя
        config.setApplicationDestinationPrefixes("/app");
    }
}

