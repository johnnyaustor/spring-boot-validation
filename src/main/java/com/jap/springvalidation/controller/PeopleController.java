package com.jap.springvalidation.controller;

import com.jap.springvalidation.dto.request.PeopleRequest;
import com.jap.springvalidation.dto.response.BadRequestResponse;
import com.jap.springvalidation.exception.ValidateException;
import com.jap.springvalidation.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping("/people")
    public ResponseEntity<?> postPeople(@RequestBody PeopleRequest peopleRequest) {
        try {
            return ResponseEntity.ok(peopleService.save(peopleRequest));
        } catch (ValidateException ex) {
            return ResponseEntity.badRequest().body(new BadRequestResponse(ex.getMessage()));
        }
    }
}
