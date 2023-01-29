package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;

public interface Lend {

    public Long create(LendDto lendDto) throws GenericException, NotFoundException;

    public Long update(long id, LendDto lendDto) throws GenericException, NotFoundException;

    public Object delete(long id) throws GenericException, NotFoundException;

}
