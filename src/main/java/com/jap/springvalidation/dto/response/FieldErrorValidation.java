package com.jap.springvalidation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Error {
    private String field;
    private Object error;
}
