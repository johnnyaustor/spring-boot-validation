package com.jap.springvalidation.service.validation;

import com.jap.springvalidation.dto.request.PeopleRequest;
import com.jap.springvalidation.exception.ValidateException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PeopleValidation {
    public void fullNameValidate(PeopleRequest peopleRequest) {
        log.debug("PeopleValidation on fullNameValidate");
        if ( peopleRequest.getFullName() == null ) throw new ValidateException("fullName not null");
    }
    public void emailValidate(PeopleRequest peopleRequest) {
        log.debug("PeopleValidation on emailValidate");
        if ( peopleRequest.getEmail() == null ) throw new ValidateException("email not null");
    }
}
