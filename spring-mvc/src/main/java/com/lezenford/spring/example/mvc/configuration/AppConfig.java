package com.lezenford.spring.example.mvc.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * Класс Spring конфигурации, содержащей настройки приложения
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.lezenford.spring.example.mvc")
public class AppConfig implements WebMvcConfigurer {
    /**
     * Настройка папки ресурсов, где будут храниться статические данные, например css или js файлы
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Создание экземпляра класса, содержащего настройки работы с шаблонами
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        // Указываем суффикс и префикс директории, хранящей темплейты thymeleaf
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // Дополнительное указание используемой кодировки для корректной работы темплейтов
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /**
     * Создание экземпляра класса, содержащего настройки шаблонизатора
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * Подключение Thymeleaf к проекту в качестве шаблонизатора
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }
}

