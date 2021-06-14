package com.lezenford.spring.example.thymeleaf.controller;

import com.lezenford.spring.example.thymeleaf.component.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ThymeleafController {
    private final List<Item> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        items.add(new Item(1, "one"));
        items.add(new Item(2, "two"));
        items.add(new Item(3, "three"));
        items.add(new Item(4, "four"));
        items.add(new Item(5, "five"));
        items.add(new Item(6, "six"));
        items.add(new Item(7, "seven"));
        items.add(new Item(8, "eight"));
        items.add(new Item(9, "nine"));
        items.add(new Item(10, "ten"));
        items.add(new Item(11, "eleven"));
        items.add(new Item(12, "twelve"));
        items.add(new Item(13, "thirteen"));
    }

    /**
     * Результат данного метода будет включаться во все экземпляры Model, участвующие в методах этого контроллера
     */
    @ModelAttribute("date")
    public Date date() {
        return new Date();
    }

    @GetMapping(value = "/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "Unknown User") String user) {
        model.addAttribute("user", user);
        model.addAttribute("items", items.stream().limit(10).collect(Collectors.toList()));
        model.addAttribute("itemsCount", items.size());
        model.addAttribute("newItem", new Item());
        return "example";
    }

    @GetMapping(value = {"/delete/{id}"})
    public String delete(@PathVariable Integer id, @RequestParam String user) {
        items.stream().filter(it -> it.getId() == id).findFirst().ifPresent(items::remove);
        return "redirect:/?user=" + user;
    }

    @PostMapping("/add")
    public String add(Item item, @RequestParam String user) {
        items.add(item);
        return "redirect:/?user=" + user;
    }
}
