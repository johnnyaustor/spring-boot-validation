package com.jap.springvalidation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PeopleRequest {
    private Long id;
    private String fullName;
    private String email;
    private Integer age;
}
