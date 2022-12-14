package ru.aston.farmershop.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughProductQuantity extends RuntimeException{

    public NotEnoughProductQuantity() {
        super("Seller dont have this amount of products");
    }

}
