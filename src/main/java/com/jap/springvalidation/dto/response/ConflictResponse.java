package com.jap.springvalidation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConflictResponse implements Serializable {
    private int statusCode = 0;
    private String message;
}
