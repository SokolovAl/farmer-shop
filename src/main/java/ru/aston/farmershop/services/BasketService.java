package ru.aston.farmershop.services;

import java.util.List;
import ru.aston.farmershop.dto.BasketProductDto;
import ru.aston.farmershop.dto.ProductDto;

public interface BasketService {

    void addProductToBasket(Long productId, Long quantity);

    void removeProductFromBasket(Long productId);

    void removeProductFromBasket(BasketProductDto basketProductDto);

    List<ProductDto> getProductsInBasket();

    void buyProductsFromBasket();

}
