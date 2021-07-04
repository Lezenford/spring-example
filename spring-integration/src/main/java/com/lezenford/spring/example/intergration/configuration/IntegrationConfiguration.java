package com.lezenford.spring.example.intergration.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;

@Log4j2
@Configuration
public class IntegrationConfiguration {

    @Bean
    public DirectChannel messageChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel logChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel successMessageChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel tooLongMessageChannel() {
        return new DirectChannel();
    }
}
