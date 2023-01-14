package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.Status;
import com.crud.kodillalibrary.library.controller.BookCopyNotFoundException;
import com.crud.kodillalibrary.library.controller.TitleNotFoundException;
import com.crud.kodillalibrary.library.domain.BookCopy;
import com.crud.kodillalibrary.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitleDbServiceTestSuite {

    @Autowired
    TitleDbService titleDbService;

    @Autowired
    BookCopyDbService bookCopyDbService;

    @Test
    void testTitleSaveTitle() throws TitleNotFoundException {
        //Given
        Title title = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);

        //When
        titleDbService.saveTitle(title);
        int id = title.getId();
        Title createdTitle = titleDbService.getTitle(id);

        //Then
        assertNotEquals(0, id);
        assertEquals(title.getId(), createdTitle.getId());
        assertEquals(title.getTitle(), createdTitle.getTitle());
        assertEquals(title.getAuthor(), createdTitle.getAuthor());
        assertEquals(title.getReleaseDate(), createdTitle.getReleaseDate());

        //CleanUp
        titleDbService.deleteTitle(id);
    }

    @Test
    void testTitleGetAllTitles() throws TitleNotFoundException {
        //Given
        Title title1 = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);
        Title title2 = new Title("Harry Potter i Komnata Tajemnic", "J.K. Rowling", 1998);

        //When
        titleDbService.saveTitle(title1);
        titleDbService.saveTitle(title2);
        int id1 = title1.getId();
        int id2 = title2.getId();
        List<Title> retrievedTitles = titleDbService.getAllTitles();

        //Then
        assertEquals(2, retrievedTitles.size());

        //CleanUp
        titleDbService.deleteTitle(id1);
        titleDbService.deleteTitle(id2);
    }

    @Test
    void testTitleSaveWithBookCopies() throws TitleNotFoundException {
        //Given
        BookCopy bookCopy1 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(Status.AVAILABLE);

        Title title = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);

        title.getBookCopies().add(bookCopy1);
        title.getBookCopies().add(bookCopy2);

        bookCopy1.setTitle(title);
        bookCopy2.setTitle(title);

        //When
        titleDbService.saveTitle(title);
        int id = title.getId();

        //Then
        assertNotEquals(0, id);
        assertEquals(2, bookCopyDbService.getAllBookCopies().size());

        //CleanUp
        titleDbService.deleteTitle(id);
    }

    @Test
    void testGetTitleThrowsException() {
        //Given
        //When&Then
        assertThrows(TitleNotFoundException.class, () -> titleDbService.getTitle(999));
    }

    @Test
    void testDeleteTitleThrowsException() {
        //Given
        //When&Then
        assertThrows(TitleNotFoundException.class, () -> titleDbService.deleteTitle(999));
    }

    @Test
    void testNumberAvailableCopies() throws BookCopyNotFoundException, TitleNotFoundException {
        //Given
        BookCopy bookCopy1 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(Status.AVAILABLE);
        BookCopy bookCopy3 = new BookCopy(Status.RENTED);
        BookCopy bookCopy4 = new BookCopy(Status.DAMAGED);

        Title title1 = new Title("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", 1997);
        Title title2 = new Title("Harry Potter i Komnata Tajemnic", "J.K. Rowling", 1998);

        title1.getBookCopies().add(bookCopy1);
        title1.getBookCopies().add(bookCopy2);
        title1.getBookCopies().add(bookCopy3);
        title1.getBookCopies().add(bookCopy4);

        bookCopy1.setTitle(title1);
        bookCopy2.setTitle(title1);
        bookCopy3.setTitle(title1);
        bookCopy4.setTitle(title1);

        //When
        titleDbService.saveTitle(title1);
        int id1 = title1.getId();
        titleDbService.saveTitle(title2);
        int id2 = title2.getId();

        //Then
        assertEquals(2, titleDbService.numberAvailableCopies(id1));
        assertEquals(0, titleDbService.numberAvailableCopies(id2));

        //CleanUp
        titleDbService.deleteTitle(id1);
        titleDbService.deleteTitle(id2);
    }
}