package com.lezenford.spring.example.context.component;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Объект Buyer имеет scope prototype, это значит, что при обращении к контексту за экземпляром этого класса
 * каждый раз будет получен новый экземпляр объекта. Это можно будет отследить через уведомление в консоли
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Buyer {
    public Buyer() {
        System.out.println("Created new Buyer");
    }
}
