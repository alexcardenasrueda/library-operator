package com.unir.libraryoperator.facade;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
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
public class PersonFacade {

    @Value("${getPersonById.url}")
    private String getPersonByIdUrl;


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
}
