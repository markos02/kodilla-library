package com.crud.kodillalibrary.library.repository;

import com.crud.kodillalibrary.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends CrudRepository<Title, Integer> {

    @Override
    Title save(Title title);

    @Override
    List<Title> findAll();

    @Override
    Optional<Title> findById(Integer id);
}
