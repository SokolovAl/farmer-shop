package ru.aston.farmershop.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoProductsInBasketException extends RuntimeException{

    public NoProductsInBasketException() {
        super("No products in basket!");
    }

}
