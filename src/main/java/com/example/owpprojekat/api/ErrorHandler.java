package com.example.owpprojekat.api;

import com.example.owpprojekat.api.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ErrorResponse.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorResponse handle(ErrorResponse e) {
        return e;
    }
}
