package ru.aston.farmershop.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.ProductDto;
import ru.aston.farmershop.services.BasketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @PostMapping
    void addProductToCart(@RequestParam String productId, @RequestParam Long quantity){
        basketService.addProductToBasket(Long.valueOf(productId), quantity);
    }

    @GetMapping
    List<ProductDto> getProductsInBasket(){
        return basketService.getProductsInBasket();
    }

    @DeleteMapping("/{productId}")
    void deleteProductFromBasket(@PathVariable Long productId){
        basketService.removeProductFromBasket(productId);
    }

    @GetMapping("/buy")
    void buyProductFromBasket(){
        basketService.buyProductsFromBasket();
    }

}
