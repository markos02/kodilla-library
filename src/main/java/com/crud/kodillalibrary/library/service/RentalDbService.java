package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.BookCopyNotFoundException;
import com.crud.kodillalibrary.library.controller.BookNotAvailableException;
import com.crud.kodillalibrary.library.controller.RentalNotFoundException;
import com.crud.kodillalibrary.library.controller.TitleNotFoundException;
import com.crud.kodillalibrary.library.domain.RentABook;
import com.crud.kodillalibrary.library.domain.Rental;
import com.crud.kodillalibrary.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalDbService {

    private final RentalRepository repository;
    private final BookCopyDbService bookCopyDbService;
    private final TitleDbService titleDbService;

    public Rental saveRental(Rental rental) {
        return repository.save(rental);
    }

    public Rental rentABook(RentABook rentABook) throws BookNotAvailableException, BookCopyNotFoundException, TitleNotFoundException {

        Integer titleId = rentABook.getTitle().getId();
        Integer bookCopyId = titleDbService.getTitle(titleId).findAvailableCopy();
        bookCopyDbService.changeStatus(bookCopyId,Status.RENTED);

        return repository.save(new Rental(
                        bookCopyDbService.getBookCopy(bookCopyId),
                        rentABook.getReader(),
                        rentABook.getDateFrom(),
                        rentABook.getDateTo()
                )
        );
    }

    public List<Rental> getAllRentals() {
        return repository.findAll();
    }

    public Rental getRental(Integer id) throws RentalNotFoundException {
        return repository.findById(id).orElseThrow(RentalNotFoundException::new);
    }

    public void deleteRental(int id) throws RentalNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RentalNotFoundException();
        }
    }
}
