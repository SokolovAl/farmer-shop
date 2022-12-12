package ru.aston.farmershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.farmershop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
