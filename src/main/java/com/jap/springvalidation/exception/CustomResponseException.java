package com.jap.springvalidation.exception;

import com.jap.springvalidation.dto.response.ConflictResponse;
import com.jap.springvalidation.dto.response.FieldErrorValidation;
import com.jap.springvalidation.dto.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class CustomResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<?> handleConflictException(ConflictException ex) {
        log.debug("error: handleConflictException");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ConflictResponse(ex.getStatusCode(), ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.debug("error: handleMethodArgumentNotValid");
        ErrorResponse errorResponse = new ErrorResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.getErrors().add(new FieldErrorValidation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

}