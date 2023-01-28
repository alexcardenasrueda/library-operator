package com.unir.libraryoperator.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
