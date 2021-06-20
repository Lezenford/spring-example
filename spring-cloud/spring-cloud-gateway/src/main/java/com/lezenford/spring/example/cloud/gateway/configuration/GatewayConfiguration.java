package com.lezenford.spring.example.cloud.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Создание маршрутов
 */
@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //Данная конфигурация говорит, что все запросы, пришедшие на указанную ссылку будут перенаправлены на сервис, зарегистрированный в
                // Eureka как CLIENT
                //Обратите внимание, здесь заменяется только адрес самого сервиса, а все вложенные пути и их параметры остаются такими же,
                //т.е. запрос на адрес http://localhost:8081/client перенаправляется на lb://CLIENT/client
                .route("clientRoute", r -> r.path("/client/**")
                        //Протокол lb - Load Balancing. В данном случае сервером для балансировки выступает сама Eureka, что и позволяет
                        // динамически подставлять адреса активных нод.
                        //В конфигурации у client указан порт 0, что говорит о запуске на случайном порту,
                        //а значит итоговый реальный порт клиента будет знать только сервер Eureka после того, как клиент на нем зарегистрируется
                        //Так же ничего не мешает здесь использовать классический http протокол и абсолютные ссылки, если использование Load
                        // Balancer не предусмотрено
                        .uri("lb://CLIENT"))
                .build();
    }
}
