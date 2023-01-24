package com.unir.libraryoperator.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
    public class BookDto {

    private long bookId;
    @NotNull(message = "ISBN must not be null")
    private long isbn;
    @NotEmpty(message = "Book name must not be empty")
    private String name;
    @NotEmpty(message = "Author must not be empty")
    private String author;
    private int publicationYear;
    private String synopsis;
    private String image;
}
