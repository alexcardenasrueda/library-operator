package com.unir.libraryoperator.facade;

import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.domain.dto.PersonDto;
import com.unir.libraryoperator.domain.entity.LendEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
//@RequiredArgsConstructor
@Slf4j
public class BookFacade {

    @Value("${getBook.url}")
    private String getBookUrl;

    @Value("${getBooks.url}")
    private String getBooksUrl;

    @Autowired
    private RestTemplate restTemplate;

    public BookDto getBook(long id){
        restTemplate.getMessageConverters();
        try{
            //String url= String.format(getBookUrl, id);
            BookDto result = restTemplate.getForObject(getBooksUrl, BookDto.class);
            return result;
        }catch (Exception e){
            log.error("Client error: {}, Person with ID {}", e.getMessage(), id);
            e.printStackTrace();
            return null;
        }
    }

    public List<BookDto> getBooks(){
        try{
            String url= String.format(getBooksUrl);
            return (List<BookDto>) restTemplate.getForObject(String.format(getBooksUrl), BookDto.class);
        }catch (HttpClientErrorException e){
            log.error("Client error: {}, Person with ID {}", e.getStatusCode());
            return null;
        }
    }
}
