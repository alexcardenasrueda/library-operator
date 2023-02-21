package com.unir.libraryoperator.facade;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.ElasticBookDto;
import com.unir.libraryoperator.domain.dto.ElasticPersonDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
import java.net.URI;
import java.net.URISyntaxException;
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
public class PersonFacade {

    @Value("${getPersonById.url}")
    private String getPersonByIdUrl;
    @Value("${postCreatePerson.url}")
    private String postCreateUrl;
    private final RestTemplate restTemplate;

    public PersonDto getById(long id) {
        System.out.println(String.format(getPersonByIdUrl, id));
        try {
            ResponseEntity<PersonDto> response = restTemplate.getForEntity(String.format(getPersonByIdUrl, id),
                    PersonDto.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}}", e.getStatusCode());
            return null;
        }
    }

    public ElasticPersonDto create(ElasticPersonDto request) {
        try {
            System.out.println(String.format(postCreateUrl, request));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(postCreateUrl);
            ResponseEntity<ElasticPersonDto> result = restTemplate.postForEntity(uri, request, ElasticPersonDto.class);
            return result.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}}", e.getStatusCode());
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
