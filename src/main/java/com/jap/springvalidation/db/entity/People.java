package com.jap.springvalidation.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class People {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String email;
    private Integer age;
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = new Date(System.currentTimeMillis());
    }
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }
}
