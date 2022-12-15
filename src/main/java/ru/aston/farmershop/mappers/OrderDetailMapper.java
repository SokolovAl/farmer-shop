package ru.aston.farmershop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.aston.farmershop.dto.OrderDetailDto;
import ru.aston.farmershop.entities.OrderDetails;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderDetailMapper {
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    OrderDetailDto toOrderDetailDto(OrderDetails orderDetails);

}
