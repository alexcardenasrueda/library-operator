package com.unir.libraryoperator.facade;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.ElasticBookDto;
import com.unir.libraryoperator.exception.GenericException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
  @Value("${postDeleteBook.url}")
  private String deleteCreateUrl;
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

  public ElasticBookDto create(ElasticBookDto request) throws GenericException {
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
      throw new GenericException(e.getMessage());
    }
  }

  public void delete(long id) throws GenericException {
    try {
      String url = String.format(deleteCreateUrl, id);
      System.out.println(url);
      restTemplate.delete(url);
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}}", e.getStatusCode());
    }
  }
}
