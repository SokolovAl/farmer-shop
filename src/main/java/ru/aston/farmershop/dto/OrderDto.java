package ru.aston.farmershop.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    String id;
    String totalCost;
    List<OrderDetailDto> products;

}
