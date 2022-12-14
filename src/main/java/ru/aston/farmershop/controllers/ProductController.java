package ru.aston.farmershop.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.ProductDto;
import ru.aston.farmershop.entities.Product;
import ru.aston.farmershop.mappers.ProductMapper;
import ru.aston.farmershop.services.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private final ProductMapper productMapper;


    @GetMapping()
    List<ProductDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return products.stream()
            .map(productMapper::toProductDto)
            .collect(Collectors.toList());
    }


}
