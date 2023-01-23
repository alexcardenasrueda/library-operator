package com.unir.libraryoperator.repository;


import com.unir.libraryoperator.domain.dto.BookDto;
import com.unir.libraryoperator.domain.entity.BookEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findAll();

    @Override
    Optional<BookEntity> findById(Long aLong);

    /**
     * @param request
     * @return BookEntity Object with saved book
     */
    BookEntity save(BookDto request);

    /**
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    default <S extends BookEntity> S save(S entity) {
        return null;
    }
}
