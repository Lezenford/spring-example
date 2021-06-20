package com.lezenford.spring.example.rest.controller;

import com.lezenford.spring.example.rest.component.Product;
import com.lezenford.spring.example.rest.controller.dto.ProductDto;
import com.lezenford.spring.example.rest.controller.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class TestController {
    private final List<Product> products = new ArrayList<>();

    {
        products.add(new Product(1, "tea", 5.0));
        products.add(new Product(2, "coffee", 10.0));
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return products.stream().map(ProductMapper.MAPPER::fromProduct).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable int id) {
        return products.stream().filter(it -> it.getId() == id).map(ProductMapper.MAPPER::fromProduct).findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        products.removeIf(it -> it.getId() == id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public void addProduct(@RequestBody ProductDto product) {
        products.add(ProductMapper.MAPPER.toProduct(product));
    }

    @GetMapping
    public void exception() {
        throw new IllegalArgumentException("test exception");
    }
}
