package com.lezenford.spring.example.context.service;

import com.lezenford.spring.example.context.component.Buyer;
import com.lezenford.spring.example.context.component.Shop;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Provider;

/**
 * Аннотация @Service это синоним @Component. Для контекста обе эти аннотации имеют один и тот же эффект
 */
@Service
public class BuyerService {
    private final Shop shop;
    private final BuyerFactory buyerFactory;

    /**
     * Класс Provider позволяет скрыть обращение к контексту для получения экземпляра конкретного класса.
     * Так как класс Buyer имеет scope prototype, то запрос экземпляра каждый раз будет возвращать новый объект из контекста.
     * Подключается отдельной зависимостью в pom.xml
     */
    private final Provider<Buyer> buyer;

    public BuyerService(Shop shop, BuyerFactory buyerFactory, Provider<Buyer> buyer) {
        this.shop = shop;
        this.buyerFactory = buyerFactory;
        this.buyer = buyer;
    }

    /**
     * Аннотация @PostConstruct выполняет указанный метод после того как объект полностью создан.
     * Подключается отдельной зависимостью в pom.xml
     */
    @PostConstruct
    public void test() {
        System.out.println("Start BuyerService");

        shop.refreshAvailableItems();

        /*
          Запрос нового Buyer через фабрику
         */
        shop.serveBuyer(buyerFactory.createBuyer());

        /*
        Запрос нового Buyer через Provider
         */
        shop.serveBuyer(buyer.get());
    }
}
