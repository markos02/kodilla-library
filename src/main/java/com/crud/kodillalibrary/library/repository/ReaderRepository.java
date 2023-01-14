package com.crud.kodillalibrary.library.repository;

import com.crud.kodillalibrary.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Integer> {

    @Override
    Reader save(Reader reader);

    @Override
    Optional<Reader> findById(Integer id);

    List<Reader> findByFirstName(String firstName);

    @Override
    List<Reader> findAll();

    @Override
    void deleteById(Integer id);

}
