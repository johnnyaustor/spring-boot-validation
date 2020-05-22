package com.jap.springvalidation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    private int statusCode = 0;

    public ConflictException(int statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }
}
