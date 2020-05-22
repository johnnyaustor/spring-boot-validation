package com.jap.springvalidation.service.validation;

import com.jap.springvalidation.db.repository.PeopleRepository;
import com.jap.springvalidation.exception.ConflictException;
import com.jap.springvalidation.utils.PeopleStatus;
import org.springframework.stereotype.Component;

@Component
public class PeopleValidation {

    private PeopleRepository peopleRepository;

    public PeopleValidation(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public void emailAvailable(String email) {
        Boolean exists = peopleRepository.existsByEmail(email);
        if (exists) throw new ConflictException(PeopleStatus.EMAIL_NOT_AVAILABLE.getStatusCode(), "Email Not Available");
    }
}
