package ru.aston.farmershop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.OrderDetailDto;
import ru.aston.farmershop.dto.OrderDto;
import ru.aston.farmershop.entities.Order;
import ru.aston.farmershop.entities.OrderDetails;
import ru.aston.farmershop.mappers.OrderDetailMapper;
import ru.aston.farmershop.mappers.OrderMapper;
import ru.aston.farmershop.services.OrderDetailService;
import ru.aston.farmershop.services.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    private final OrderDetailMapper orderDetailMapper;

    private final OrderMapper orderMapper;

    @GetMapping
    List<OrderDto> getUserOrders() {
        List<Order> orderList = orderService.getOrders();
        List<OrderDetails> orderDetailsList = orderDetailService.getOrderDetailsByOrders(orderList);

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orderList) {
            List<OrderDetailDto> filteredOrderDetailsDto = orderDetailsList.stream()
                .filter(orderDetails -> orderDetails.getOrder().equals(order))
                .map(orderDetailMapper::toOrderDetailDto)
                .collect(Collectors.toList());
            orderDtoList.add(orderMapper.toOrderDto(order, filteredOrderDetailsDto));
        }
        return orderDtoList;
    }

}
