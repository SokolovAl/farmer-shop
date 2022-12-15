package ru.aston.farmershop.services;

import java.util.List;
import ru.aston.farmershop.entities.Order;

public interface OrderService {

    Order saveOrder(Order order);

    List<Order> getOrders();

}
