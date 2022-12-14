package ru.aston.farmershop.util;

import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.farmershop.util.exceptions.NoProductsInBasketException;
import ru.aston.farmershop.util.exceptions.NoSuchProductInBasketException;
import ru.aston.farmershop.util.exceptions.NotEnoughMoneyException;
import ru.aston.farmershop.util.exceptions.NotEnoughProductQuantity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorInfo> logAndGetErrorInfo(HttpServletRequest req, Exception e,
        ErrorType errorType) {
        return ResponseEntity.status(errorType.getStatus())
            .body(new ErrorInfo(req.getRequestURL(), errorType,
                e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(HttpServletRequest req, EntityNotFoundException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.ENTITY_NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(HttpServletRequest req, NotEnoughMoneyException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.NOT_ENOUGH_MONEY);
    }

    @ExceptionHandler(NotEnoughProductQuantity.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(HttpServletRequest req, NotEnoughProductQuantity ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.NOT_ENOUGH_QUANTITY);
    }

    @ExceptionHandler(NoProductsInBasketException.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(HttpServletRequest req, NoProductsInBasketException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.NO_PRODUCTS_IN_BASKET);
    }

    @ExceptionHandler(NoSuchProductInBasketException.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(HttpServletRequest req, NoSuchProductInBasketException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.NO_SUCH_PRODUCT_IN_BASKET);
    }

}
