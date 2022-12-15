package ru.aston.farmershop.services;

import java.util.List;
import ru.aston.farmershop.entities.Order;
import ru.aston.farmershop.entities.OrderDetails;

public interface OrderDetailService {

    void save(OrderDetails orderDetails);

    List<OrderDetails> getOrderDetailsByOrders(List<Order> orderList);

}
