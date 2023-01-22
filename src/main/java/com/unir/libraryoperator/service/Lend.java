package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.exception.GenericException;

import java.util.List;

public interface Lend {

    public LendDto getById(long id);

    public LendDto create(LendDto lendDto) throws GenericException;

    public LendDto update(LendDto lendDto) throws GenericException;
}
