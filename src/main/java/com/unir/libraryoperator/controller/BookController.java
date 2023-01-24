package com.unir.libraryoperator.controller;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.service.Book;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  @Autowired
  private Book service;

  @PostMapping
  ResponseEntity<Object> createBook(@Valid @RequestBody(required = true) BookDto request)
      throws GenericException {
    return ResponseEntity.created(
            URI.create(String.format("/books/%s", service.createBook(request))))
        .build();
  }

  @PutMapping
  ResponseEntity<BookDto> updateBook(@RequestParam(required = true, value = "id") long id,
      @Valid @RequestBody(required = true) BookDto request)
      throws GenericException, NotFoundException {
    return ResponseEntity.ok(service.updateBook(id, request));
  }

  @DeleteMapping
   ResponseEntity<Object> deleteBook(@RequestParam(required = true, value = "id") long id)
      throws GenericException {
    return ResponseEntity.ok(service.deleteBook(id));
  }

}
