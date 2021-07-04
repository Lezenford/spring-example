package com.lezenford.spring.example.intergration.controller;

import com.lezenford.spring.example.intergration.component.TextMessage;
import com.lezenford.spring.example.intergration.integration.TextMessageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final TextMessageGateway gateway;

    @GetMapping
    public void send(@RequestParam String message) {
        final TextMessage textMessage = new TextMessage();
        textMessage.setText(message);
        gateway.process(textMessage);
    }
}
