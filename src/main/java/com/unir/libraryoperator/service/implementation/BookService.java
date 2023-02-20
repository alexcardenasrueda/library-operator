package com.unir.libraryoperator.service.implementation;


import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.ElasticBookDto;
import com.unir.libraryoperator.domain.entity.BookEntity;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.facade.BookFacade;
import com.unir.libraryoperator.repository.BookRepository;
import com.unir.libraryoperator.service.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@PropertySource("classpath:constants.properties")
public class BookService implements Book {

  @Autowired
  BookRepository repository;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  BookFacade facade;

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
      //Integration with browser-elasticsearch
      facade.create(this.translatorRqCreateElasticSearch(request, savedBook.getBookId()));

      idBook = savedBook.getBookId();
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return idBook;
  }

  private ElasticBookDto translatorRqCreateElasticSearch(BookDto request, long bookId) {
    ElasticBookDto requestTranslated = ElasticBookDto.builder().build();
    requestTranslated = modelMapper.map(request, ElasticBookDto.class);
    requestTranslated.setId(bookId);
    return requestTranslated;
  }

  /**
   * @param id
   * @param request
   * @return
   */
  @Override
  public Long updateBook(long id, BookDto request) throws NotFoundException, GenericException {
    BookEntity save;
    try {
      BookDto bookFacade = facade.getById(id);
      System.out.println(bookFacade);

      if (bookFacade == null) {
        throw new NotFoundException(MessageFormat.format(bookNotFound, id));
      }
      BookEntity entityBook = modelMapper.map(request, BookEntity.class);
      entityBook.setBookId(id);

      save = repository.save(entityBook);

    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return save.getBookId();
  }

  /**
   * @param id
   * @return
   */
  @Override
  public Object deleteBook(long id) throws GenericException {
    try{
      BookDto book = facade.getById(id);
      if (book == null) {
        throw new NotFoundException(MessageFormat.format(bookNotFound, id));
      }
      repository.deleteById(id);
    }catch (RuntimeException | NotFoundException e){
      throw new GenericException(e.getMessage());
    }
    return null;
  }
}
