package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;

public interface Lend {

    public LendDto getById(long id) throws GenericException, NotFoundException;

    public LendDto create(LendDto lendDto) throws GenericException, NotFoundException;

    public LendDto update(LendDto lendDto) throws GenericException, NotFoundException;

    public void delete(long id) throws GenericException, NotFoundException;

}
