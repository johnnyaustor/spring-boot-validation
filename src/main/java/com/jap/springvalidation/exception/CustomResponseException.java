package com.jap.springvalidation.exception;

import com.jap.springvalidation.dto.response.Error;
import com.jap.springvalidation.dto.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class PeopleControllerException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("masuk");
        ErrorResponse errorResponse = new ErrorResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.getErrors().add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
