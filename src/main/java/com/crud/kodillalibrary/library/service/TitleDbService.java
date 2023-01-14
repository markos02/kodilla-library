package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.TitleNotFoundException;
import com.crud.kodillalibrary.library.domain.BookCopy;
import com.crud.kodillalibrary.library.domain.Title;
import com.crud.kodillalibrary.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository repository;

    public Title saveTitle(Title title) {
        return repository.save(title);
    }

    public List<Title> getAllTitles() {
        return repository.findAll();
    }

    public Title getTitle(Integer id) throws TitleNotFoundException {
        return repository.findById(id).orElseThrow(TitleNotFoundException::new);
    }

    public void deleteTitle(Integer id) throws TitleNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new TitleNotFoundException();
        }
    }

    public int numberAvailableCopies(Integer id) throws TitleNotFoundException {

        int result = 0;

        if (repository.findById(id).isPresent()) {
            List<BookCopy> bookCopies = repository.findById(id).get().getBookCopies();
            for (BookCopy copy : bookCopies) {
                if (copy.getStatus().equals(Status.AVAILABLE)) {
                    result++;
                }
            }
        } else {
            throw new TitleNotFoundException();
        }
        return result;
    }
}
