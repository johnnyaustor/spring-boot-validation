package com.jap.springvalidation;

import com.jap.springvalidation.dto.response.FieldErrorValidation;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@NoArgsConstructor
public class ErrorResult {
    List<FieldErrorValidation> errors = new ArrayList<>();

    public ErrorResult(String field, String message) {
        this.errors.add(new FieldErrorValidation(field, message));
    }
}
