package com.unir.libraryoperator.repository;


import com.unir.libraryoperator.domain.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    List<PersonEntity> findAll();
}
