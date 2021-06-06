package com.lezenford.spring.example.jpa;

import com.lezenford.spring.example.jpa.model.entity.Product;
import com.lezenford.spring.example.jpa.model.entity.User;
import com.lezenford.spring.example.jpa.model.repository.ProductRepository;
import com.lezenford.spring.example.jpa.model.repository.RoleRepository;
import com.lezenford.spring.example.jpa.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    private final static Logger log = LogManager.getLogger(JpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private final UserService userService;
    private final ProductRepository productRepository;
    private final RoleRepository roleRepository;

    public JpaApplication(UserService userService, ProductRepository productService, RoleRepository roleRepository) {
        this.userService = userService;
        this.productRepository = productService;
        this.roleRepository = roleRepository;
    }

    /**
     * Описание возможных сценариев работы и ошибок
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Начало тестового сценария");
        transactionalLazyInitTest();
        log.info("\n\n------------------------------\n\n");
        transactionalSaveAndUpdateTest();
        log.info("Конец тестового сценария");
    }

    /**
     * Демонстрация работы LAZY-полей
     */
    private void transactionalLazyInitTest() {
        //демонстрация работы LAZY - запрос к БД по данным из поля произойдет в момент обращения к полю в коде
        log.info("Запрашиваем пользователя:");
        User user = userService.findByIdWithoutTransaction(1);
        log.info("Роль подтянулась сразу: " + user.getRole().getName());
        log.info("Данные по полю продукта не запрашивались, но транзакция уже закрылась и получить их не получится");
        try {
            user.getProducts().size();
        } catch (Exception e) {
            log.info("Мы получили Lazy Exception: " + e.getMessage());
        }
        log.info("Сделаем повторный запрос, но с инициализацией поля в пределах открытой транзакции");
        user = userService.findById(1);
        log.info("Данные были запрошены в пределах транзакции и теперь мы можем к ним обратиться: " + user.getProducts().size());

        log.info("\n\n------------------------------\n\n");

        log.info("Запрос продуктов");
        Optional<Product> optionalProduct = productRepository.findById(1);
        optionalProduct.ifPresent(product -> log.info("Продукт 1: " + product));
        log.info("Поиск сока:");
        Product product = productRepository.findFirstByName("juice");
        log.info("Сок: " + product);
        log.info("Поиск продуктов дешевле 15");
        List<Product> products = productRepository.findCheaperProducts(15);
        log.info("Продукты:");
        products.forEach(log::info);
    }

    /**
     * Демонстрация работы сохранения и обновления сущностей в БД
     */
    private void transactionalSaveAndUpdateTest() {
        log.info("Создание или изменение сущностей в БД требуется явного открытия транзакции");
        User user = new User();
        user.setName("Test");
        user.setLastName("TestLastName");
        user.setRole(roleRepository.findAll().get(0));
        try {
            userService.createWithoutTransaction(user);
        } catch (Exception e) {
            log.info("Попытка создать новый объект в БД без явного открытия транзакции выдала ошибку: " + e.getMessage());
        }
        log.info("Сохраним объект с явным открытие транзакции");
        userService.create(user);
        log.info("Сохранение прошло успешно");

        log.info("Удаление продукта с ценой 15");
        log.info("Использование JpaRepository так же требует явного открытия транзакции");
        try {
            productRepository.deleteByCostWithoutTransactional(15);
        } catch (Exception e) {
            log.info("Попытка удаления без явного указания транзации вернула ошибку: " + e.getMessage());
        }
        log.info("Удаление с явно открытой транзакцией:");
        productRepository.deleteByCost(15);
        log.info("Удаление прошло успешно");

        log.info("\n\n------------------------------\n\n");

        log.info("Модификация с использованием JPQL требует помимо явного открытия транзакции еще и добавление аннотации @Modify");
        try {
            productRepository.updateCoastWithoutModify(12, 10);
        } catch (Exception e) {
            log.info("Попытка сделать update вернула ошибку: " + e.getMessage());
        }
        log.info("Обновление с открытой транзакцией и аннотацией работает корректно");
        productRepository.updateCoast(18, 10);
        log.info("Ниже видно, что обновление цены с 10 на 12 не прошло, а вот с 10 на 18 оказалось успешным");
        productRepository.findAll().forEach(log::info);
    }
}
