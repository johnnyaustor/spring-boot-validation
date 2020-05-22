package com.jap.springvalidation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jap.springvalidation.db.entity.People;
import com.jap.springvalidation.db.repository.PeopleRepository;
import com.jap.springvalidation.dto.request.PeopleRequest;
import com.jap.springvalidation.service.validation.PeopleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {

    private PeopleRepository peopleRepository;
    private PeopleValidation peopleValidation;
    private ObjectMapper objectMapper;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PeopleValidation peopleValidation, ObjectMapper objectMapper) {
        this.peopleRepository = peopleRepository;
        this.peopleValidation = peopleValidation;
        this.objectMapper = objectMapper;
    }

    public PeopleRequest save(PeopleRequest peopleRequest) {
        peopleValidation.emailAvailable(peopleRequest.getEmail());
        People people = objectMapper.convertValue(peopleRequest, People.class);
        People save = peopleRepository.save(people);
        return objectMapper.convertValue(save, PeopleRequest.class);
    }
}
