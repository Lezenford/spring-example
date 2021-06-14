package com.lezenford.spring.example.web_services.endpoint;

import com.lezenford.spring.example.web_services.repository.GreetingRepository;
import com.lezenford.spring.example.ws.api.greeting.GetGreetingRequest;
import com.lezenford.spring.example.ws.api.greeting.GetGreetingResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

/**
 * Контроллер для обработки SOAP запросов. Аннотация {@link Endpoint} для SOAP тоже самое, что {@link org.springframework.stereotype.Controller}
 * для MVC и {@link org.springframework.web.bind.annotation.RestController} для REST-интерфейсов
 */
@Endpoint
public class GreetingEndpoint {
    private static final String NAMESPACE_URI = "http://ws.example.spring.lezenford.com/api/greeting";

    private final GreetingRepository greetingRepository;

    public GreetingEndpoint(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    /**
     * {@link PayloadRoot} аналогичен {@link org.springframework.web.bind.annotation.RequestMapping} и содержит в себе данные для привязки к запросам,
     * а именно namespace схемы и название метода, который должен обрабатывать данный метод
     * {@link ResponsePayload} аналогичен {@link org.springframework.web.bind.annotation.ResponseBody} для REST-интерфейсов, он позволяет обернуть
     * результат метода в соответствующий тип объекта для SOAP-интерфейса, а так же проводит корректную XML-сериализацию
     * {@link RequestPayload} аналогичен {@link org.springframework.web.bind.annotation.RequestBody} и обозначает объект из тела запроса
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGreetingRequest")
    @ResponsePayload
    public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest request) throws DatatypeConfigurationException {
        GetGreetingResponse response = new GetGreetingResponse();
        response.setGreeting(greetingRepository.getGreeting(request.getName()));
        return response;
    }
}

