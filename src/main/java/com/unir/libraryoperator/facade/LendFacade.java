package com.unir.libraryoperator.facade;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.LendDto;
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
public class LendFacade {

    @Value("${getLendById.url}")
    private String getLendByIdUrl;

    private final RestTemplate restTemplate;

    public LendDto getById(long id) {
        try {
            ResponseEntity<LendDto> response = restTemplate.getForEntity(String.format(getLendByIdUrl, id),
                    LendDto.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}}", e.getStatusCode());
            return null;
        }
    }
}