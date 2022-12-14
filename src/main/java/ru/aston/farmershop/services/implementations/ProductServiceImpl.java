package ru.aston.farmershop.services.implementations;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.entities.Product;
import ru.aston.farmershop.repositories.ProductRepository;
import ru.aston.farmershop.services.ProductService;

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
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
