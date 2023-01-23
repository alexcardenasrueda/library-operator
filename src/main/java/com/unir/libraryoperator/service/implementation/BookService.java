package com.unir.libraryoperator.service.implementation;


import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.entity.BookEntity;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.repository.BookRepository;
import com.unir.libraryoperator.service.Book;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;

@Service
@PropertySource("classpath:constants.properties")
public class BookService implements Book {

  @Autowired
  BookRepository repository;

  @Autowired
  ModelMapper modelMapper;

  @Value("${exception.book.create_failed}")
  private String errorCreation;
  @Value("${exception.book.not_found}")
  private String bookNotFound;

  /**
   * @param request
   * @return
   */
  @Override
  public Long createBook(BookDto request) throws GenericException {
    long idBook;
    try {
      BookEntity map = modelMapper.map(request, BookEntity.class);
      BookEntity savedBook = repository.save(map);
      if (savedBook == null) {
        throw new GenericException(errorCreation);
      }
      idBook = savedBook.getBookId();
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return idBook;
  }

  /**
   * @param id
   * @param request
   * @return
   */
  @Override
  public BookDto updateBook(long id, BookDto request) throws NotFoundException, GenericException {
    BookDto book;
    try {
      Optional<BookEntity> entityBook = repository.findById(id);
      if (entityBook.isEmpty()) {
        throw new NotFoundException(MessageFormat.format(bookNotFound, id));
      }
      request.setBookId(entityBook.get().getBookId());
      BookEntity savedBook = repository.save(request);
      if (savedBook == null) {
        throw new GenericException(errorCreation);
      }
      book = modelMapper.map(entityBook.get(), BookDto.class);
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return book;
  }

  /**
   * @param id
   * @return
   */
  @Override
  public Object deleteBook(long id) throws GenericException {
    try {
      repository.deleteById(id);
    } catch (RuntimeException ex) {
      throw new GenericException(ex.getMessage());
    }
    return null;
  }
}
