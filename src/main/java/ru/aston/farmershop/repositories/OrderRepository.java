package ru.aston.farmershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.farmershop.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
