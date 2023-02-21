package com.unir.libraryoperator.controller;


import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.service.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private Person personService;

    @PostMapping
    ResponseEntity<URI> createPerson(@Valid @RequestBody(required = true) PersonDto personDto)
            throws GenericException {
        return ResponseEntity.created(
                        URI.create(String.format("library-browser/people/get_by_id/%s", personService.createPerson(personDto))))
                .build();
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<URI> updatePerson(@PathVariable long id,
                                   @Valid @RequestBody(required = true) PersonDto personDto)
            throws GenericException, NotFoundException {
        return ResponseEntity.created(
                        URI.create(String.format("library-browser/people/get_by_id/%s", personService.updatePerson(id,personDto))))
                .build();    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Object> deletePerson(@PathVariable long id)
            throws GenericException {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
