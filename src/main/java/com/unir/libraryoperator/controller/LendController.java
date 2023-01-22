package com.unir.libraryoperator.controller;


import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.service.Lend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lends")
@RequiredArgsConstructor
public class LendController {

    @Autowired
    private Lend lendService;

    @GetMapping(value ="/get-by-id", params = "id")
    ResponseEntity<LendDto> getById(@RequestParam(required = true, name = "id") long id ) throws GenericException {
        return ResponseEntity.ok(lendService.getById(id));
    }

    @PostMapping
    ResponseEntity<LendDto> createLend(@RequestBody LendDto lendDto) throws GenericException {
        return ResponseEntity.ok(lendService.create(lendDto));
    }

    @PutMapping
    ResponseEntity<LendDto> updateLend(@RequestBody LendDto lendDto) {
        return ResponseEntity.ok(LendDto.builder().build());
    }

    @DeleteMapping
    ResponseEntity<LendDto> deleteLend(@RequestBody LendDto lendDto) {
        return ResponseEntity.ok(LendDto.builder().build());
    }
}
