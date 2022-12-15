package ru.aston.farmershop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.farmershop.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByUserId(Long id);

}
