package com.jap.springvalidation.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class PeopleRequest {
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String fullName;
    @NotNull
    @Email
    private String email;
    @Min(value = 17)
    @Max(value = 55)
    @NotNull
    private Integer age;
}
