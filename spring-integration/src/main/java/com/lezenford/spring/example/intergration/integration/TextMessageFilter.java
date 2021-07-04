package com.lezenford.spring.example.intergration.integration;

import com.lezenford.spring.example.intergration.component.TextMessage;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class TextMessageFilter {
    @Filter(inputChannel = "messageChannel", outputChannel = "successMessageChannel", discardChannel = "tooLongMessageChannel")
    public boolean filter(TextMessage textMessage) {
        return textMessage.getText().length() < 10;
    }
}
