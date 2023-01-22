package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.exception.GenericException;

import java.util.List;

public interface Person {

    public List<PersonDto> getAll() throws GenericException;

    public PersonDto getById(long id);
}
