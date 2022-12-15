package ru.aston.farmershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.farmershop.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
