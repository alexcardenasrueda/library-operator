package com.unir.libraryoperator.controller;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.service.Book;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {


  @Autowired
  private Book service;

  @PostMapping
  ResponseEntity<URI> createBook(@Valid @RequestBody(required = true) BookDto request)
      throws GenericException {
    return ResponseEntity.created(
            URI.create(String.format("library-browser/books/get_by_id/%s", service.createBook(request))))
        .build();
  }

  @PutMapping(value = "/{id}")
  ResponseEntity<URI> updateBook(@PathVariable long id,
      @Valid @RequestBody(required = true) BookDto request)
      throws GenericException, NotFoundException {
    return ResponseEntity.created(
                    URI.create(String.format("library-browser/books/get_by_id/%s", service.updateBook(id,request))))
            .build();
  }

  @DeleteMapping(value = "/{id}")
  ResponseEntity<Object> deleteBook(@PathVariable long id)
      throws GenericException {
    service.deleteBook(id);
    return ResponseEntity.noContent().build();
  }

}
