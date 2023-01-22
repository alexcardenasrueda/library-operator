package com.unir.libraryoperator.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LendDto {

    private long lendId;
    private LocalDate lendDate;
    private LocalDate estimatedReturnDate;
    private LocalDate realReturnDate;
    private long personId;
    private String personName;
    private long bookId;
    private String bookName;
}