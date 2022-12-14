package ru.aston.farmershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private String id;

    private String name;

    private String quantity;

    private String price;

    private String type;

    private String sellerId;

}
