package com.unir.libraryoperator.controller;


import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.service.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private Person service;

    @GetMapping
    ResponseEntity<List<PersonDto>> getAll() throws GenericException {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value ="/getById")
    ResponseEntity<PersonDto> getById(@RequestParam(required = true, name = "id") long id ) throws GenericException {
        return ResponseEntity.ok(service.getById(id));
    }
}
