package com.unir.libraryoperator.controller;


import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.exception.NotFoundException;
import com.unir.libraryoperator.service.Lend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/lends")
@RequiredArgsConstructor
public class LendController {


    @Autowired
    private Lend lendService;

    @PostMapping
    ResponseEntity<URI> createLend(@Valid @RequestBody(required = true) LendDto lendDto)
            throws GenericException, NotFoundException {
            return ResponseEntity.created(
                            URI.create(String.format("library-browser/lends/get_by_id/%s", lendService.create(lendDto))))
                    .build();
    }
    @PutMapping(value = "/{id}")
    ResponseEntity<URI> updateLend(@PathVariable long id,
                                   @Valid @RequestBody(required = true) LendDto lendDto)
            throws GenericException, NotFoundException {
        return ResponseEntity.created(
                        URI.create(String.format("library-browser/lends/get_by_id/%s", lendService.update(id,lendDto))))
                .build();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Object> deleteLend(@PathVariable long id)
            throws GenericException, NotFoundException {
        lendService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


