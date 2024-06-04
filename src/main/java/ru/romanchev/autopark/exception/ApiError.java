package ru.romanchev.autopark.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ApiError {
    public ApiError(String message, String reason, HttpStatus status) {
        this.message = message;
        this.reason = reason;
        this.status = status;
    }

    private String message;

    private String reason;

    private HttpStatus status;

    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}
