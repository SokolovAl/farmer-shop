package ru.aston.farmershop.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.dto.BasketProductDto;
import ru.aston.farmershop.dto.ProductDto;
import ru.aston.farmershop.entities.Product;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.ProductMapper;
import ru.aston.farmershop.security.UserDetailsImpl;
import ru.aston.farmershop.services.BasketService;
import ru.aston.farmershop.services.ProductService;
import ru.aston.farmershop.util.exceptions.NoProductsInBasketException;
import ru.aston.farmershop.util.exceptions.NoSuchProductInBasketException;
import ru.aston.farmershop.util.exceptions.NotEnoughProductQuantity;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final ProductService productService;

    private final RedisTemplate<String, String> redisTemplate;

    private final ProductMapper productMapper;

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
//        User user = getAuthenticatedUser();
//        Product product = productService.getProductById(productId);
//        redisTemplate.opsForList().remove()
//        redisTemplate.opsForList().leftPush(user.getId().toString(), product.getId().toString());
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
}
