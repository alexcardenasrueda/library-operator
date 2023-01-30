package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;

public interface Book {

  Long createBook(BookDto request) throws GenericException;

  Long updateBook(long id, BookDto request) throws NotFoundException, GenericException;

  Object deleteBook(long id) throws GenericException;


}
