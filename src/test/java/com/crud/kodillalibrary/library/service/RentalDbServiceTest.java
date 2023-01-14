package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.*;
import com.crud.kodillalibrary.library.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalDbServiceTest {

    @Autowired
    RentalDbService rentalDbService;

    @Autowired
    TitleDbService titleDbService;

    @Autowired
    BookCopyDbService bookCopyDbService;

    @Autowired
    ReaderDbService readerDbService;

    @Test
    void saveRental() throws RentalNotFoundException, TitleNotFoundException, ReaderNotFoundException {
        //Given
        Title title = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);
        BookCopy bookCopy = new BookCopy(Status.AVAILABLE);
        bookCopy.setTitle(title);
        title.getBookCopies().add(bookCopy);
        Reader reader = new Reader("John", "Smith", new Date(2023, Calendar.JANUARY, 12));
        Rental rental = new Rental(bookCopy, reader, new Date(2023, Calendar.JANUARY, 12), new Date(2023, Calendar.FEBRUARY, 12));

        //When
        readerDbService.saveReader(reader);
        int readerId = reader.getId();
        titleDbService.saveTitle(title);
        int titleId = title.getId();
        rentalDbService.saveRental(rental);
        int rentalId = rental.getId();
        Rental retrievedRental = rentalDbService.getRental(rentalId);

        //Then
        assertNotEquals(0, rentalId);
        assertEquals(rental.getId(), retrievedRental.getId());
        assertEquals(1, bookCopyDbService.getAllBookCopies().size());
        assertEquals(1, readerDbService.getAllReaders().size());

        //CleanUp
        rentalDbService.deleteRental(rentalId);
        titleDbService.deleteTitle(titleId);
        readerDbService.deleteReader(readerId);
    }

    @Test
    void getAllRentals() throws RentalNotFoundException, TitleNotFoundException, ReaderNotFoundException {
        //Given
        Title title = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);
        BookCopy bookCopy1 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(Status.AVAILABLE);
        bookCopy1.setTitle(title);
        bookCopy2.setTitle(title);
        title.getBookCopies().add(bookCopy1);
        title.getBookCopies().add(bookCopy2);
        Reader reader = new Reader("John", "Smith", new Date(2023, Calendar.JANUARY, 12));
        Rental rental1 = new Rental(bookCopy1, reader, new Date(2023, Calendar.JANUARY, 12), new Date(2023, Calendar.FEBRUARY, 12));
        Rental rental2 = new Rental(bookCopy2, reader, new Date(2023, Calendar.FEBRUARY, 12), new Date(2023, Calendar.MARCH, 12));

        //When
        readerDbService.saveReader(reader);
        int readerId = reader.getId();
        titleDbService.saveTitle(title);
        int titleId = title.getId();
        rentalDbService.saveRental(rental1);
        int rentalId1 = rental1.getId();
        rentalDbService.saveRental(rental2);
        int rentalId2 = rental2.getId();

        //Then
        assertEquals(2, rentalDbService.getAllRentals().size());

        //CleanUp
        rentalDbService.deleteRental(rentalId1);
        rentalDbService.deleteRental(rentalId2);
        titleDbService.deleteTitle(titleId);
        readerDbService.deleteReader(readerId);
    }

    @Test
    void testGetRentalThrowsException() {
        //Given
        //When&Then
        assertThrows(RentalNotFoundException.class, () -> rentalDbService.getRental(999));
    }

    @Test
    void testDeleteRentalThrowsException() {
        //Given
        //When&Then
        assertThrows(RentalNotFoundException.class, () -> rentalDbService.deleteRental(999));
    }

    @Test
    void testRentABook() throws RentalNotFoundException, TitleNotFoundException, BookCopyNotFoundException, BookNotAvailableException, ReaderNotFoundException {

        //Given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 0, 14);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, 2, 14);
        Date dateTo = calendar.getTime();

        Title title = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);
        BookCopy bookCopy1 = new BookCopy(Status.RENTED);
        BookCopy bookCopy2 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy3 = new BookCopy(Status.DAMAGED);
        bookCopy1.setTitle(title);
        bookCopy2.setTitle(title);
        bookCopy3.setTitle(title);
        title.getBookCopies().add(bookCopy1);
        title.getBookCopies().add(bookCopy2);
        title.getBookCopies().add(bookCopy3);
        Reader reader = new Reader("John", "Smith", new Date(2023, Calendar.JANUARY, 12));
        RentABook rentABook = new RentABook(title, reader, dateFrom, dateTo);

        //When
        titleDbService.saveTitle(title);
        int titleId = title.getId();
        readerDbService.saveReader(reader);
        int readerId = reader.getId();
        Rental retrievedRental = rentalDbService.rentABook(rentABook);
        int rentalId = retrievedRental.getId();

        //Then
        assertNotNull(retrievedRental);
        assertThrows(BookNotAvailableException.class, () -> rentalDbService.rentABook(rentABook));

        //CleanUp
        rentalDbService.deleteRental(rentalId);
        titleDbService.deleteTitle(titleId);
        readerDbService.deleteReader(readerId);
    }
}