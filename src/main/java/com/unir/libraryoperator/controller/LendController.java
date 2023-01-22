package com.unir.libraryoperator.controller;


import com.unir.libraryoperator.domain.dto.LendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lends")
@RequiredArgsConstructor
public class LendController {

    @GetMapping
    ResponseEntity<LendDto> getAll() {
        return ResponseEntity.ok(LendDto.builder().build());
    }
}
