package ru.aston.farmershop.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.aston.farmershop.dto.OrderDetailDto;
import ru.aston.farmershop.dto.OrderDto;
import ru.aston.farmershop.entities.Order;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderDto toOrderDto(Order order, List<OrderDetailDto> products);

}
