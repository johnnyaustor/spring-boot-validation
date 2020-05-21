package com.jap.springvalidation.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PeopleRequest implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private Integer age;
}
