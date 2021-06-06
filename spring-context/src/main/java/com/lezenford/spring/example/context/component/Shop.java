package com.lezenford.spring.example.context.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Shop {
    /**
     * Аннотация @Autowired показывает контексту Spring, что данное поле должно быть заполнено в автоматическом режиме элементом из контекста.
     * Spring осуществит это либо через setter, либо через рефлексию.
     * Не рекомендуется к использованию вне тестовых классов
     */
    @Autowired
    private Seller seller;

    /**
     * Данное поле будет заполнено благодаря конструктору. В классе Shop есть единственный доступный для работы констурктор, потому при создании
     * экземпляра класса он будет выбран автоматически. Так как это конструктор с параметрами, все параметры будут подобраны из контекста.
     * Если каких то элементов нет в контексте - старт контекста упадет с ошибкой
     */
    private final Worker worker;

    public Shop(Worker worker) {
        this.worker = worker;
    }

    public void serveBuyer(Buyer buyer) {
        System.out.println("New buyer in shop");
        /**
         * Так как поле seller заполняется не в конструкторе, то теоретически возможна ситуация, при которой выполнение этого метода
         * будет инициировано до того как сработает @Autowired. Это один из аргументов в пользу использования inject через конструктор
         */
        if (seller != null) {
            seller.sell();
        }
    }

    public void refreshAvailableItems() {
        System.out.println("Get new items for shop");
        worker.work();
    }

    /**
     * Аннотация @PostConstruct выполняет указанный метод после того как объект полностью создан.
     * Это значит, что все поля помеченные @Autowired уже заполнены.
     * Подключается отдельной зависимостью в pom.xml
     */
    @PostConstruct
    public void init() {
        System.out.println("Shop full initialize");
    }
}
