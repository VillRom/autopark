package ru.romanchev.autopark.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRequestException(final RequestException e, WebRequest webRequest) {
        log.info(e.getMessage());
        return new ApiError(e.getMessage(), "Переданные данные некорректны "
                + webRequest.getDescription(false), HttpStatus.BAD_REQUEST);
    }
}
