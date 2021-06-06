package com.lezenford.spring.example.jpa.model.repository;

import com.lezenford.spring.example.jpa.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Пример реализации репозитория для Product
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Генерация запроса происходит на основании имени метода, если он не нарушает соглашение
     */
    Product findFirstByName(String name);

    /**
     * Для построения произвольных запросов на JPQL, HQL или native SQL можно использовать аннотацию {@link Query}
     */
    @Query("select p from Product p where p.cost < :cost")
    List<Product> findCheaperProducts(int cost);

    /**
     * Явное открытие транзакции требуется для каждой операции записи или изменения
     */
    @Transactional
    void deleteByCost(int cost);

    /**
     * Данный запрос не сработает, т.к. транзакция не открыта явно
     */
    @Query("delete from Product p where p.cost = :cost")
    void deleteByCostWithoutTransactional(int cost);

    /**
     * Обновление сущности через {@link Query} требует помимо явного открытия транзакции так же использование {@link Modifying}
     */
    @Modifying
    @Transactional
    @Query("update Product p set p.cost = :cost where p.cost = :oldCost")
    void updateCoast(int cost, int oldCost);

    /**
     * Данный запрос не сработает, т.к. нет аннотации {@link Modifying}
     */
    @Transactional
    @Query("update Product p set p.cost = :cost where p.cost = :oldCost")
    void updateCoastWithoutModify(int cost, int oldCost);
}
