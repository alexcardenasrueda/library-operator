package com.unir.libraryoperator.repository;

import com.unir.libraryoperator.domain.entity.LendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LendRepository extends JpaRepository<LendEntity, Long> {

    List<LendEntity> findAll();

    Optional<LendEntity> findById(Long id);

    //LendEntity save(LendEntity lend);
}
