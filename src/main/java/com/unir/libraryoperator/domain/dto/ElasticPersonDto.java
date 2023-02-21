package com.unir.libraryoperator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticPersonDto {
  private long id;
  private String uid;
  private String name;
  private String lastName;
  private String address;
  private String phone;
  private String email;
}
