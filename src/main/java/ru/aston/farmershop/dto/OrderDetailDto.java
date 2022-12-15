package ru.aston.farmershop.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDto {

    String productId;
    String productName;
    BigDecimal quantity;
    BigDecimal price;

}
