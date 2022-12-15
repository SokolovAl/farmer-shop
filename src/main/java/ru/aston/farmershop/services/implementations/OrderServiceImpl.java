package ru.aston.farmershop.services.implementations;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.entities.Order;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.repositories.OrderRepository;
import ru.aston.farmershop.security.UserDetailsImpl;
import ru.aston.farmershop.services.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        User user = getAuthenticatedUser();
        return orderRepository.getOrdersByUserId(user.getId());
    }
}
