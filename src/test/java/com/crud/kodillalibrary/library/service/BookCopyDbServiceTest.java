package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.BookCopyNotFoundException;
import com.crud.kodillalibrary.library.domain.BookCopy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookCopyDbServiceTest {

    @Autowired
    BookCopyDbService bookCopyDbService;

    @Autowired
    TitleDbService titleDbService;

    @Test
    void saveBookCopy() throws BookCopyNotFoundException {
        //Given
        BookCopy bookCopy = new BookCopy(Status.AVAILABLE);

        //When
        bookCopyDbService.saveBookCopy(bookCopy);
        int id = bookCopy.getId();
        BookCopy retrievedBookCopy = bookCopyDbService.getBookCopy(id);

        //Then
        assertNotEquals(0, id);
        assertEquals(bookCopy.getId(), retrievedBookCopy.getId());
        assertEquals(bookCopy.getStatus(), retrievedBookCopy.getStatus());
        assertEquals(bookCopy.getTitle(), retrievedBookCopy.getTitle());

        //CleanUp
        bookCopyDbService.deleteBookCopy(id);
    }

    @Test
    void getAllBookCopies() throws BookCopyNotFoundException {
        //Given
        BookCopy bookCopy1 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(Status.AVAILABLE);

        //When
        bookCopyDbService.saveBookCopy(bookCopy1);
        int id1 = bookCopy1.getId();
        bookCopyDbService.saveBookCopy(bookCopy2);
        int id2 = bookCopy2.getId();

        List<BookCopy> retrievedBookCopyList = bookCopyDbService.getAllBookCopies();

        //Then
        assertEquals(2, retrievedBookCopyList.size());

        //CleanUp
        bookCopyDbService.deleteBookCopy(id1);
        bookCopyDbService.deleteBookCopy(id2);
    }

    @Test
    void testGetBookCopyThrowsException() {
        //Given
        //When&Then
        assertThrows(BookCopyNotFoundException.class, () -> bookCopyDbService.getBookCopy(999));
    }

    @Test
    void testDeleteBookCopyThrowsException() {
        //Given
        //When&Then
        assertThrows(BookCopyNotFoundException.class, () -> bookCopyDbService.deleteBookCopy(999));
    }

    @Test
    void testIsAvailable() throws BookCopyNotFoundException {
        //Given
        BookCopy bookCopy1 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(Status.DAMAGED);
        BookCopy bookCopy3 = new BookCopy(Status.RENTED);

        //When
        bookCopyDbService.saveBookCopy(bookCopy1);
        int id1 = bookCopy1.getId();
        bookCopyDbService.saveBookCopy(bookCopy2);
        int id2 = bookCopy2.getId();
        bookCopyDbService.saveBookCopy(bookCopy3);
        int id3 = bookCopy3.getId();

        //Then
        assertTrue(bookCopyDbService.isAvailable(id1));
        assertFalse(bookCopyDbService.isAvailable(id2));
        assertFalse(bookCopyDbService.isAvailable(id3));

        //CleanUp
        bookCopyDbService.deleteBookCopy(id1);
        bookCopyDbService.deleteBookCopy(id2);
        bookCopyDbService.deleteBookCopy(id3);
    }

    @Test
    void testChangeStatus() throws BookCopyNotFoundException {
        //Given
        BookCopy bookCopy = new BookCopy(Status.AVAILABLE);

        //When
        bookCopyDbService.saveBookCopy(bookCopy);
        int id = bookCopy.getId();
        bookCopyDbService.changeStatus(id, Status.RENTED);
        BookCopy retrievedBookCopy = bookCopyDbService.getBookCopy(id);

        //Then
        assertEquals(Status.RENTED, retrievedBookCopy.getStatus());

        //CleanUp
        bookCopyDbService.deleteBookCopy(id);
    }
}