package ru.aston.farmershop.services.implementations;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.entities.Product;
import ru.aston.farmershop.repositories.ProductRepository;
import ru.aston.farmershop.services.ProductService;
import ru.aston.farmershop.util.exceptions.NotEnoughProductQuantity;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void reduceAmountOfProduct(Long id, Long quantity) {
        Product product = getProductById(id);
        Long currentQuantity = product.getQuantity();
        if (currentQuantity <= 0) {
            throw new NotEnoughProductQuantity();
        }
        long resultQuantity = currentQuantity - quantity;
        if (resultQuantity < 0) {
            throw new NotEnoughProductQuantity();
        }
        product.setQuantity(resultQuantity);
        productRepository.save(product);
    }
}
