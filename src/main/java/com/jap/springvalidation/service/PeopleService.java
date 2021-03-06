package com.jap.springvalidation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jap.springvalidation.db.entity.People;
import com.jap.springvalidation.db.repository.PeopleRepository;
import com.jap.springvalidation.dto.request.PeopleRequest;
import com.jap.springvalidation.service.validation.PeopleValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final ObjectMapper objectMapper;
    private final PeopleValidation peopleValidation;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, ObjectMapper objectMapper, PeopleValidation peopleValidation) {
        this.peopleRepository = peopleRepository;
        this.objectMapper = objectMapper;
        this.peopleValidation = peopleValidation;
    }

    public PeopleRequest save(PeopleRequest peopleRequest) {
        log.debug("PeopleService on Save");
        peopleValidation.fullNameValidate(peopleRequest.getFullName());
        peopleValidation.emailValidate(peopleRequest.getEmail());
        People people = objectMapper.convertValue(peopleRequest, People.class);
        People save = peopleRepository.save(people);
        return objectMapper.convertValue(save, PeopleRequest.class);
    }
}
