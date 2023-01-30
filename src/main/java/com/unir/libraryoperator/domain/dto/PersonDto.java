package com.unir.libraryoperator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private long personId;

    @NotEmpty(message = "uid person must not be empty")
    private String uid;
    @NotEmpty(message = "name person must not be empty")
    private String name;
    private String lastName;
    private String address;
    private String phone;
    @NotEmpty(message = "email person must not be empty")
    private String email;
}
