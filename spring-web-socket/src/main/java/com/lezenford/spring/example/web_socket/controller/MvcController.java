package com.lezenford.spring.example.web_socket.controller;

import com.lezenford.spring.example.web_socket.component.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class MvcController {
    private final SimpMessagingTemplate template;
    private final List<Item> items = new ArrayList<>();

    public MvcController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @ModelAttribute("items")
    public List<Item> getItems() {
        return items;
    }

    @GetMapping({"/", "/index.html"})
    public String get() {
        return "index";
    }

    /**
     * Обработчик WebSocket
     */
    @MessageMapping("/item") // вход — канал, куда JS-клиент отправляет сообщения
    @SendTo("/topic/items") // выход — канал, на который подписывается JS-клиент
    public Item addItem(Item item) {
        items.add(item);
        return item;
    }

    /**
     * Контроллер для добавления сообщений в обход сокета
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Void> put(@RequestBody String body) {
        if (body != null && !body.trim().isEmpty()) {
            Item item = new Item(body);
            items.add(item);
            // оповещаем WebSocket-клиентов
            sendItem(item);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private void sendItem(Item item) {
        this.template.convertAndSend("/topic/items", item);
    }
}

