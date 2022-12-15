package ru.aston.farmershop.services.implementations;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.entities.Order;
import ru.aston.farmershop.entities.OrderDetails;
import ru.aston.farmershop.repositories.OrderDetailsRepository;
import ru.aston.farmershop.services.OrderDetailService;
import ru.aston.farmershop.util.exceptions.NoCompleteOrdersException;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public void save(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrders(List<Order> orderList) {
        if(orderList.isEmpty()){
            throw new NoCompleteOrdersException();
        }
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderList.forEach(order -> orderDetailsList.addAll(orderDetailsRepository.findOrderDetailsByOrderId(order.getId())));
        return orderDetailsList;
    }


}
