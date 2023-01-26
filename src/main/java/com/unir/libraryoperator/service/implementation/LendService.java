package com.unir.libraryoperator.service.implementation;

import com.google.common.base.Function;
import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.domain.entity.LendEntity;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.facade.BookFacade;
import com.unir.libraryoperator.repository.LendRepository;
import com.unir.libraryoperator.service.Lend;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BookFacade personFacade;



    @Override
    public LendDto create(LendDto lendDto) throws GenericException, NotFoundException {
        LendEntity lend;


        BookDto book = personFacade.getBook(lendDto.getPersonId());
        if (book == null){

        }
        try{
            lend = modelMapper.map(lendDto, LendEntity.class);
            lendRepository.saveAndFlush(lend);
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
        return getById(lend.getLendId());
    }

    @Override
    public LendDto update(LendDto lendDto) throws GenericException, NotFoundException {
        try{
            boolean lendExist = (lendRepository.existsById(lendDto.getLendId()));
            if (!lendExist) {
                throw new NotFoundException(MessageFormat.format("Lend {0} not found", lendDto.getLendId()));
            }
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
        return create(lendDto);
    }

    @Override
    public void delete(long id) throws GenericException, NotFoundException {
        try{
            LendDto lend = getById(id);
            lendRepository.deleteById(lend.getLendId());
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public LendDto getById(long id) throws GenericException,NotFoundException {
        LendDto lend;
        try{
            Optional<LendEntity> lendOpt = lendRepository.findById(id);
            if (lendOpt.isEmpty()) {
                throw new NotFoundException(MessageFormat.format("Lend {0} not found", id));
            }
            lend = modelMapper.map(lendOpt.get(), LendDto.class);
        }catch (RuntimeException e){
            throw new GenericException(e.getMessage());
        }
        return lend;
    }
}
