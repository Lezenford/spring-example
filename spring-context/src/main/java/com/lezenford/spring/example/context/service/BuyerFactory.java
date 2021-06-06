package com.lezenford.spring.example.context.service;

import com.lezenford.spring.example.context.component.Buyer;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * Аннотация @Service это синоним @Component. Для контекста обе эти аннотации имеют один и тот же эффект
 */
@Service
public class BuyerFactory {

    /**
     * Аннотация @Lookup говорит Spring, что данный метод должен возвращать экземпляр класса Buyer из контекста,
     * не зависимо от того, что написано в теле метода.
     * Обращаться к этому методу можно только из-вне класса BuyerFactory
     */
    @Lookup
    public Buyer createBuyer() {
        return null;
    }
}
