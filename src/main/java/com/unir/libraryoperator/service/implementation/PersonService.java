package com.unir.libraryoperator.service.implementation;


import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.domain.entity.PersonEntity;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.facade.PersonFacade;
import com.unir.libraryoperator.repository.PersonRepository;
import com.unir.libraryoperator.service.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@PropertySource("classpath:constants.properties")
public class PersonService implements Person {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonFacade personFacade;

    @Value("${exception.person.create_failed}")
    private String errorCreationPerson;
    @Value("${exception.person.not_found}")
    private String personNotFound;

    @Override
    public Long createPerson(PersonDto personDto) throws GenericException {
        long idPerson;
        try {
            PersonEntity prsonEntity = modelMapper.map(personDto, PersonEntity.class);
            PersonEntity savedPerson = personRepository.save(prsonEntity);
            if (savedPerson == null) {
                throw new GenericException(errorCreationPerson);
            }
            idPerson = savedPerson.getPersonId();
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return idPerson;
    }

    @Override
    public Long updatePerson(long id, PersonDto personDto) throws NotFoundException, GenericException {
        PersonEntity save;
        try {
            PersonDto person = personFacade.getById(id);
            if (person == null) {
                throw new NotFoundException(MessageFormat.format(personNotFound, id));
            }
            PersonEntity personEntity = modelMapper.map(personDto, PersonEntity.class);
            personEntity.setPersonId(id);
            save = personRepository.save(personEntity);

        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return save.getPersonId();
    }

    @Override
    public Object deletePerson(long id) throws GenericException {
        try{
            PersonDto person = personFacade.getById(id);
            if (person == null) {
                throw new NotFoundException(MessageFormat.format(personNotFound, id));
            }
            personRepository.deleteById(id);
        }catch (RuntimeException | NotFoundException e){
            throw new GenericException(e.getMessage());
        }
        return null;
    }
}
