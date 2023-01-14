package com.crud.kodillalibrary.library.service;

import com.crud.kodillalibrary.library.controller.ReaderNotFoundException;
import com.crud.kodillalibrary.library.domain.Reader;
import com.crud.kodillalibrary.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderDbServiceTest {

    @Autowired
    ReaderDbService readerDbService;

    @Test
    void saveReader() throws ReaderNotFoundException {
        //Given
        Reader reader = new Reader("John", "Smith", new Date(2023, 01, 12));

        //When
        readerDbService.saveReader(reader);
        int id = reader.getId();
        Reader createdReader = readerDbService.getReader(id);

        //Then
        assertNotEquals(0, id);
        assertEquals(reader.getId(), createdReader.getId());
        assertEquals(reader.getLastName(), createdReader.getLastName());
        assertEquals(reader.getFirstName(), createdReader.getFirstName());
        assertEquals(reader.getCreated(), createdReader.getCreated());

        //CleanUp
        readerDbService.deleteReader(id);
    }

    @Test
    void getAllReaders() throws ReaderNotFoundException {
        //Given
        Reader reader1 = new Reader("John", "Smith", new Date(2023, 01, 12));
        Reader reader2 = new Reader("Jane", "Smith", new Date(2023, 01, 10));

        //When
        readerDbService.saveReader(reader1);
        int id1 = reader1.getId();
        readerDbService.saveReader(reader2);
        int id2 = reader2.getId();
        List<Reader> retrievedReaders = readerDbService.getAllReaders();

        //Then
        assertEquals(2, retrievedReaders.size());

        //CleanUp
        readerDbService.deleteReader(id1);
        readerDbService.deleteReader(id2);
    }

    @Test
    void testGetReaderThrowsException() {
        //Given
        //When&Then
        assertThrows(ReaderNotFoundException.class, () -> readerDbService.getReader(999));
    }

    @Test
    void testDeleteReaderThrowsException() {
        //Given
        //When&Then
        assertThrows(ReaderNotFoundException.class, () -> readerDbService.deleteReader(999));
    }
}