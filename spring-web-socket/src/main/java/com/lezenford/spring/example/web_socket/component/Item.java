package com.lezenford.spring.example.web_socket.component;

/**
 * DTO для обмена через WebSocket
 */
public class Item {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Item() {
    }

    public Item(String content) {
        this.content = content;
    }
}

