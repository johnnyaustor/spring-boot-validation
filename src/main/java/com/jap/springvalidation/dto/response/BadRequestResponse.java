package com.jap.springvalidation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestResponse {
    private String errorMessage;
}
