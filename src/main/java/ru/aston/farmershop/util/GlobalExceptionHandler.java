package ru.aston.farmershop.util;

import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.farmershop.util.exceptions.NotEnoughMoneyException;

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

}
