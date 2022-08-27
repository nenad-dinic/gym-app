package com.example.owpprojekat.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends RuntimeException {

    String message;
    int code;

    public ErrorResponse(String message, int code) {
        this.code = code;
        this.message = message;
    }
}
