package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.controller.ReaderNotFoundException;
import com.crud.kodillalibrary.library.domain.Reader;
import com.crud.kodillalibrary.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository repository;

    public Reader saveReader(Reader reader) {
        return repository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    public Reader getReader(Integer id) throws ReaderNotFoundException {
        return repository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public void deleteReader(Integer id) throws ReaderNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ReaderNotFoundException();
        }
    }
}
