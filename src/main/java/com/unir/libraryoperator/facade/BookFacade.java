package com.unir.libraryoperator.facade;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.entity.BookEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:constants.properties")
public class BookFacade {

  @Value("${getBookById.url}")
  private String getBookByIdUrl;

  private final RestTemplate restTemplate;

  public BookDto getById(long id) {
    try {
      System.out.println(String.format(getBookByIdUrl, id));
      ResponseEntity<BookDto> response = restTemplate.getForEntity(String.format(getBookByIdUrl, id),
          BookDto.class);
      return response.getBody();
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}}", e.getStatusCode());
      return null;
    }
  }
}
