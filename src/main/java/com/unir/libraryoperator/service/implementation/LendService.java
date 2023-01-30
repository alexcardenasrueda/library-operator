package com.unir.libraryoperator.service.implementation;

import com.google.common.base.Function;
import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.domain.entity.BookEntity;
import com.unir.libraryoperator.domain.entity.LendEntity;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.facade.BookFacade;
import com.unir.libraryoperator.facade.LendFacade;
import com.unir.libraryoperator.facade.PersonFacade;
import com.unir.libraryoperator.repository.LendRepository;
import com.unir.libraryoperator.service.Lend;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class LendService implements Lend {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LendRepository lendRepository;

    @Autowired
    BookFacade bookFacade;

    @Autowired
    LendFacade lendFacade;

    @Autowired
    PersonFacade personFacade;

    @Value("${exception.lend.not_found}")
    private String lendNotFound;

    @Value("${exception.book.not_found}")
    private String bookNotFound;
    @Value("${exception.person.not_found}")
    private String personNotFound;

    @Value("${exception.person.create_failed}")
    private String errorCreationPerson;


    /**
     *
     * @param lendDto
     * @return
     * @throws GenericException
     * @throws NotFoundException
     */
    @Override
    public Long create(LendDto lendDto) throws GenericException, NotFoundException {
        long idLend;
        try{
            BookDto book = bookFacade.getById(lendDto.getBookId());
            if (book == null) {
                throw new NotFoundException(MessageFormat.format(bookNotFound, lendDto.getBookId()));
            }

            PersonDto person = personFacade.getById(lendDto.getPersonId());
            if (person == null) {
                throw new NotFoundException(MessageFormat.format(personNotFound, lendDto.getPersonId()));
            }
            LendEntity lendEntity = modelMapper.map(lendDto, LendEntity.class);
            LendEntity savedLend = lendRepository.save(lendEntity);
            if (savedLend == null) {
                throw new GenericException(errorCreationPerson);
            }
            idLend = savedLend.getLendId();
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
        return idLend;
    }

    /***
     *
     * @param id
     * @param lendDto
     * @return
     * @throws GenericException
     * @throws NotFoundException
     */
    @Override
    public Long update(long id, LendDto lendDto) throws GenericException, NotFoundException {
        LendEntity savedLend;
        try{
            LendDto lend = lendFacade.getById(id);
            if (lend == null) {
                throw new NotFoundException(MessageFormat.format(lendNotFound, id));
            }

            BookDto book = bookFacade.getById(lendDto.getBookId());
            if (book == null) {
                throw new NotFoundException(MessageFormat.format(bookNotFound, lendDto.getBookId()));
            }

            PersonDto person = personFacade.getById(lendDto.getPersonId());
            if (person == null) {
                throw new NotFoundException(MessageFormat.format(personNotFound, lendDto.getPersonId()));
            }

            LendEntity lendEntity = modelMapper.map(lendDto, LendEntity.class);
            lendEntity.setLendId(id);
            savedLend = lendRepository.save(lendEntity);
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
        return savedLend.getLendId();
    }

    /**
     *
     * @param id
     * @return
     * @throws GenericException
     * @throws NotFoundException
     */
    @Override
    public Object delete(long id) throws GenericException, NotFoundException {
        try{
            LendDto lend = lendFacade.getById(id);
            if (lend == null) {
                throw new NotFoundException(MessageFormat.format(lendNotFound, id));
            }
            lendRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
        return null;
    }
}
