package com.jap.springvalidation.utils;

import lombok.Getter;

@Getter
public enum PeopleStatus {
    EMAIL_NOT_AVAILABLE(100);

    private int statusCode;

    PeopleStatus(int statusCode) {
        this.statusCode = statusCode;
    }
}
