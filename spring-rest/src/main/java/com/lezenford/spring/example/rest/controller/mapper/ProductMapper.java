package com.lezenford.spring.example.rest.controller.mapper;

import com.lezenford.spring.example.rest.component.Product;
import com.lezenford.spring.example.rest.controller.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Данный класс сгенерирует преобразование класса к его Дто и обратно на основе аргументов методов
 */
@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "name", target = "item")
    Product toProduct(ProductDto productDto);

    //При наличии аннотаций Mapping на методе toProduct, аннотация InheritInverseConfiguration будет производить обратную обработку этих правил.
    //В отсутствии Mapping использовать InheritInverseConfiguration не обязательно
    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);

    List<Product> toProducts(List<ProductDto> productDtoList);

    List<ProductDto> fromProducts(List<Product> productList);
}
