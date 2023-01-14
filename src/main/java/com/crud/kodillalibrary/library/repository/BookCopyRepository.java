package com.crud.kodillalibrary.library.repository;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {

    @Override
    List<BookCopy> findAll();

    List<BookCopy> findBookCopyByStatus(Status status);


}
