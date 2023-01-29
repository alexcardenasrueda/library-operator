package com.unir.libraryoperator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "personId must not be null")
    private long personId;
    private String personName;
    @NotNull(message = "bookId must not be null")
    private long bookId;
    private String bookName;
}