package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.BookCopyNotFoundException;
import com.crud.kodillalibrary.library.domain.BookCopy;
import com.crud.kodillalibrary.library.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookCopyDbService {

    private final BookCopyRepository repository;

    public List<BookCopy> getAllBookCopies() {
        return repository.findAll();
    }

    public BookCopy getBookCopy(Integer id) throws BookCopyNotFoundException {
        return repository.findById(id).orElseThrow(BookCopyNotFoundException::new);
    }

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return repository.save(bookCopy);
    }

    public void deleteBookCopy(Integer id) throws BookCopyNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new BookCopyNotFoundException();
        }
    }

    public boolean isAvailable(Integer id) {
        return repository.findById(id).get().getStatus().equals(Status.AVAILABLE);
    }

    public BookCopy changeStatus(Integer id, Status newStatus) throws BookCopyNotFoundException {
        Optional<BookCopy> bookCopy = repository.findById(id);
        BookCopy tempBookCopy;
        if (bookCopy.isPresent()) {
            tempBookCopy = bookCopy.get();
            tempBookCopy.setStatus(newStatus);
        } else {
            throw new BookCopyNotFoundException();
        }
        return repository.save(tempBookCopy);
    }

}
