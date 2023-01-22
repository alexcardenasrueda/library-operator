package com.unir.libraryoperator.domain.dto;

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
    private long isbn;
    private String name;
    private String author;
    private int publicationYear;
    private String synopsis;
    private String image;
}
