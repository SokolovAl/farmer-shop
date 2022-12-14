package ru.aston.farmershop.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchProductInBasketException extends RuntimeException{

    public NoSuchProductInBasketException() {
        super("No such product in basket!");
    }


}
