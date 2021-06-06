package com.lezenford.spring.example.mvc.controller;

import com.lezenford.spring.example.mvc.component.Product;
import com.lezenford.spring.example.mvc.configuration.AppConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер с префиксом product
 * Позволяет отобразить доступные продукты, а так же добавить в список новые
 */
@Controller
@RequestMapping("/product")
public class BasicController {

    private final List<Product> products = new ArrayList<>();

    {
        Product product = new Product();
        product.setName("Coffee");
        product.setCost(10);
        products.add(product);
    }

    /**
     * Отображает экран со списком продуктов
     *
     * @param model модель, куда следует помещать данные, которые должны быть обработаны Thymeleaf при формировании страницы из темплейта
     * @return название темплейта без учета суффикса и префикса, указанных в настройках {@link AppConfig}
     */
    @GetMapping
    public String main(Model model) {
        // Список продуктов, которые требуется отобразить
        model.addAttribute("products", products);

        // Шаблон для заполнения POST формы для добавления продуктов
        model.addAttribute("newProduct", new Product());
        return "products";
    }

    /**
     * Обработка POST-запроса с информацией о новом продукте
     *
     * @param product результат POST-запроса
     * @return перенаправление на другую страницу
     */
    @PostMapping("/add")
    public String testPostRequest(Product product) {
        // Добавляем продукт в список
        products.add(product);

        // Делаем перенаправление на основную страницу, где этот продукт будет отображаться
        return "redirect:/product";
    }
}
