package com.unir.libraryoperator.facade;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.ElasticBookDto;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
  @Value("${postCreateBook.url}")
  private String postCreateUrl;
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

  public ElasticBookDto create(ElasticBookDto request) {
    try {
      System.out.println(String.format(postCreateUrl, request));
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
      URI uri = new URI(postCreateUrl);
      ResponseEntity<ElasticBookDto> result = restTemplate.postForEntity(uri, request, ElasticBookDto.class);
      return result.getBody();

    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}}", e.getStatusCode());
      return null;
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
