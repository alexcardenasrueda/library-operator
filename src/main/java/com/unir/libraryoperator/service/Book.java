package com.unir.libraryoperator.service;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import java.util.List;

public interface Book {

  List<BookDto> getAll();

  BookDto getById(long id);

  Long createBook(BookDto request) throws GenericException;

  BookDto updateBook(long id, BookDto request) throws NotFoundException, GenericException;

  Object deleteBook(long id) throws GenericException;


}
