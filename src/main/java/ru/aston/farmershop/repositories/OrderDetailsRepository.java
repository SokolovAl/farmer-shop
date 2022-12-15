package ru.aston.farmershop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.farmershop.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    List<OrderDetails> findOrderDetailsByOrderId(Long id);

}
