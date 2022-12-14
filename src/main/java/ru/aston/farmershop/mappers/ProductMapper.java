package ru.aston.farmershop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.aston.farmershop.dto.BasketProductDto;
import ru.aston.farmershop.dto.ProductDto;
import ru.aston.farmershop.entities.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "sellerId", source = "product.user.id")
    @Mapping(target = "type", source = "product.type.type")
    ProductDto toProductDto(Product product);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "quantity", source = "quantity")
    BasketProductDto toBasketProductDto(Product product, String quantity);
}
