package ru.aston.farmershop.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.entities.OrderDetails;
import ru.aston.farmershop.repositories.OrderDetailsRepository;
import ru.aston.farmershop.services.OrderDetailService;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public void save(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }
}
