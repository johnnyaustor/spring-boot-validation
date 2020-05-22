package com.jap.springvalidation.service.validation;

import com.jap.springvalidation.exception.ValidateException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PeopleValidation {
    public void fullNameValidate(String fullName) {
        log.debug("PeopleValidation on fullNameValidate");
        if ( fullName == null ) throw new ValidateException("fullName not null");
    }
    public void emailValidate(String email) {
        log.debug("PeopleValidation on emailValidate");
        if ( email == null ) throw new ValidateException("email not null");
    }
}
