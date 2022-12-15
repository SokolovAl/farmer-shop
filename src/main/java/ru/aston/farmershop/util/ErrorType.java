package ru.aston.farmershop.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    ENTITY_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_QUANTITY(HttpStatus.BAD_REQUEST),
    NO_PRODUCTS_IN_BASKET(HttpStatus.BAD_REQUEST),
    NO_ORDERS(HttpStatus.BAD_REQUEST),
    NO_SUCH_PRODUCT_IN_BASKET(HttpStatus.BAD_REQUEST);


    private final HttpStatus status;
}
