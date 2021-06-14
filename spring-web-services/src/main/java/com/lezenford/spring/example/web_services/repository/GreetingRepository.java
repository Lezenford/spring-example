package com.lezenford.spring.example.web_services.repository;

import com.lezenford.spring.example.ws.api.greeting.Greeting;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

@Repository
public class GreetingRepository {
    public Greeting getGreeting(String name) throws
            DatatypeConfigurationException {
        Assert.notNull(name, "name can't be null");
        Greeting greeting = new Greeting();
        greeting.setText(String.format("Hello, %s", name));
        greeting.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
        return greeting;
    }
}
