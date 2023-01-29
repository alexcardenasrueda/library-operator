package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;

import java.util.List;

public interface Person {

    Long createPerson(PersonDto personDto) throws GenericException;

    Long updatePerson(long id, PersonDto personDto) throws NotFoundException, GenericException;

    Object deletePerson(long id) throws GenericException;
}
