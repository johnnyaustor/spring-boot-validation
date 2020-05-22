package com.jap.springvalidation.db.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@Entity
public class People {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String fullName;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @Min(value = 17)
    @Max(value = 55)
    @NotNull
    private Integer age;
}
