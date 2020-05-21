package com.jap.springvalidation.controller;

import com.jap.springvalidation.dto.request.PeopleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PeopleController {

    @PostMapping("/people")
    public ResponseEntity<?> postPeople(@Valid @RequestBody PeopleRequest peopleRequest) {
        return ResponseEntity.ok(peopleRequest);
    }
}
