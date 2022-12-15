package ru.aston.farmershop.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.farmershop.dto.BasketProductDto;
import ru.aston.farmershop.dto.ProductDto;
import ru.aston.farmershop.entities.Order;
import ru.aston.farmershop.entities.OrderDetails;
import ru.aston.farmershop.entities.Product;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.ProductMapper;
import ru.aston.farmershop.security.UserDetailsImpl;
import ru.aston.farmershop.services.BasketService;
import ru.aston.farmershop.services.OrderDetailService;
import ru.aston.farmershop.services.OrderService;
import ru.aston.farmershop.services.PaymentService;
import ru.aston.farmershop.services.ProductService;
import ru.aston.farmershop.services.UserService;
import ru.aston.farmershop.util.exceptions.NoProductsInBasketException;
import ru.aston.farmershop.util.exceptions.NoSuchProductInBasketException;
import ru.aston.farmershop.util.exceptions.NotEnoughProductQuantity;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final ProductService productService;

    private final RedisTemplate<String, String> redisTemplate;

    private final ProductMapper productMapper;

    private final PaymentService paymentService;

    private final UserService userService;

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public void addProductToBasket(Long productId, Long quantity) {
        ObjectMapper mapper = new ObjectMapper();
        User user = getAuthenticatedUser();
        Product product = productService.getProductById(productId);

        if (quantity > product.getQuantity()) {
            throw new NotEnoughProductQuantity();
        }

        BasketProductDto basketProductDto = productMapper.toBasketProductDto(product,
            quantity.toString());
        try {
            String jsonBasketProduct = mapper.writeValueAsString(basketProductDto);
            redisTemplate.opsForList().leftPush(user.getId().toString(), jsonBasketProduct);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeProductFromBasket(Long productId) {
        ObjectMapper mapper = new ObjectMapper();
        User user = getAuthenticatedUser();
        List<BasketProductDto> basketProductDtos = getBasketProductsDto();

        List<BasketProductDto> productDtoList = basketProductDtos.stream()
            .filter(product -> product.getProductId().equals(productId.toString()))
            .collect(Collectors.toList());

        if (productDtoList.isEmpty()) {
            throw new NoSuchProductInBasketException();
        }

        BasketProductDto basketProduct = productDtoList.get(0);

        try {
            String jsonBasketProduct = mapper.writeValueAsString(basketProduct);
            redisTemplate.opsForList().remove(user.getId().toString(), 1, jsonBasketProduct);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeProductFromBasket(BasketProductDto basketProductDto) {
        User user = getAuthenticatedUser();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonBasketProduct = mapper.writeValueAsString(basketProductDto);
            redisTemplate.opsForList().remove(user.getId().toString(), 1, jsonBasketProduct);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<BasketProductDto> getBasketProductsDto() {
        List<BasketProductDto> basketProductDtos = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        User user = getAuthenticatedUser();

        Long size = redisTemplate.opsForList().size(user.getId().toString());
        List<String> jsonProducts = redisTemplate.opsForList()
            .range(user.getId().toString(), 0, size);

        if (jsonProducts != null) {
            try {
                for (String jsonProduct : jsonProducts) {
                    basketProductDtos.add(mapper.readValue(jsonProduct, BasketProductDto.class));
                }
            } catch (JsonProcessingException e) {
                System.out.println(e);
            }
        } else {
            throw new NoProductsInBasketException();
        }
        return basketProductDtos;
    }

    @Override
    public List<ProductDto> getProductsInBasket() {
        List<BasketProductDto> basketProductDtos = getBasketProductsDto();

        List<Product> productList = new ArrayList<>();

        for (BasketProductDto basketProduct : basketProductDtos) {
            Product product = productService.getProductById(
                Long.valueOf(basketProduct.getProductId()));
            product.setQuantity(basketProduct.getQuantity());
            productList.add(product);
        }

        return productList.stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void buyProductsFromBasket() {
        List<BasketProductDto> basketProductDtos = getBasketProductsDto();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        BigDecimal totalOrderCost = new BigDecimal(0);
        for (BasketProductDto basketProduct : basketProductDtos) {
            Product product = productService.getProductById(
                Long.valueOf(basketProduct.getProductId()));
            // User-seller
            User user = userService.getUserById(product.getUser().getId());

            BigDecimal productPrice = product.getPrice();
            BigDecimal productQuantity = BigDecimal.valueOf(basketProduct.getQuantity());
            BigDecimal totalProductPrice = productPrice.multiply(productQuantity);

            // Reducing amount of product
            productService.reduceAmountOfProduct(product.getId(), basketProduct.getQuantity());
            // Transferring money to seller for product
            paymentService.transferMoneyToSeller(user, totalProductPrice);
            OrderDetails orderDetails =
                OrderDetails.builder()
                    .product(product)
                    .price(productPrice)
                    .quantity(basketProduct.getQuantity())
                    .build();
            orderDetailsList.add(orderDetails);
            totalOrderCost = totalOrderCost.add(totalProductPrice);
        }
        Order order = Order.builder()
            //.orderStatus(OrderStatus.PAID)
            .user(getAuthenticatedUser())
            .totalCost(totalOrderCost)
            .build();

        Order savedOrder = orderService.saveOrder(order);
        for (OrderDetails orderDetail : orderDetailsList) {
            orderDetail.setOrder(savedOrder);
            orderDetailService.save(orderDetail);
        }
        // remove bought products from basket
        for (BasketProductDto basketProduct : basketProductDtos) {
            removeProductFromBasket(basketProduct);
        }
    }
}
