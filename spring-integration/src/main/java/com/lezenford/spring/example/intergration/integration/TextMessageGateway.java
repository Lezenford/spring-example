package com.lezenford.spring.example.intergration.integration;

import com.lezenford.spring.example.intergration.component.TextMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TextMessageGateway {

    @Gateway(requestChannel = "message_channel.input", headers = @GatewayHeader(name = "gatewayName", value = "TextMessageGateway"))
    void process(TextMessage textMessage);
}
