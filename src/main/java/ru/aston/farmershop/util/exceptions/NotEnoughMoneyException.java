package ru.aston.farmershop.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super("User dont have enough money!");
    }

}
