package ru.aston.farmershop.util;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorInfo {
    private final StringBuffer url;
    private final ErrorType type;
    private final String exceptionMsg;
    private final LocalDateTime timeStamp;
}
