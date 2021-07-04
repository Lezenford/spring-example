package com.lezenford.spring.example.intergration.integration;

import com.lezenford.spring.example.intergration.component.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

@Log4j2
@MessageEndpoint
@RequiredArgsConstructor
public class TextMessageActivator {
    private final DirectChannel messageChannel;
    private final DirectChannel logChannel;

    @ServiceActivator(inputChannel = "message_channel.input")
    public void logChannelActivator(Message<TextMessage> message) {
        messageChannel.send(message);
        logChannel.send(message);
    }

    @ServiceActivator(inputChannel = "logChannel")
    public void logChannelActivator(@Payload TextMessage payload, @Headers Map<String, Object> headerMap) {
        final String headers = headerMap.entrySet().stream().map(it -> it.getKey() + " : " + it.getValue()).reduce("", (a, b) -> a + "\n" + b);
        log.info("logChannel takes new message:\n" + payload + "\nwith headers: " + headers);
    }

    @ServiceActivator(inputChannel = "successMessageChannel")
    public void successMessageChannelActivator(@Payload TextMessage payload, @Headers Map<String, Object> headerMap) {
        final String headers = headerMap.entrySet().stream().map(it -> it.getKey() + " : " + it.getValue()).reduce("", (a, b) -> a + "\n" + b);
        log.info("successMessageChannel takes new message:\n" + payload + "\nwith headers: " + headers);
    }

    @ServiceActivator(inputChannel = "tooLongMessageChannel")
    public void tooLongMessageChannelActivator(@Payload TextMessage payload, @Headers Map<String, Object> headerMap) {
        final String headers = headerMap.entrySet().stream().map(it -> it.getKey() + " : " + it.getValue()).reduce("", (a, b) -> a + "\n" + b);
        log.info("tooLongMessageChannel takes new message:\n" + payload + "\nwith headers: " + headers);
    }
}
