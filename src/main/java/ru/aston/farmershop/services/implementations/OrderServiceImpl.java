package ru.aston.farmershop.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.entities.Order;
import ru.aston.farmershop.repositories.OrderRepository;
import ru.aston.farmershop.services.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
