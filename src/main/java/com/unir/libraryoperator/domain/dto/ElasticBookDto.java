package com.unir.libraryoperator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticBookDto {

    private long id;
    private long isbn;
    private String name;
    private String author;
    private int publicationYear;
    private String synopsis; // To full text
    private String image;
}

