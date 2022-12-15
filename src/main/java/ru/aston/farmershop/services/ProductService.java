package ru.aston.farmershop.services;

import java.util.List;
import ru.aston.farmershop.entities.Product;

/**
 * Service that works with {@link Product} entity.
 */
public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void reduceAmountOfProduct(Long id, Long quantity);

}
