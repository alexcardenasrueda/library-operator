package com.unir.libraryoperator.domain.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LendDto {

    private long lendId;
    private LocalDate lendDate;
    private LocalDate estimatedReturnDate;
    private LocalDate realReturnDate;
}
