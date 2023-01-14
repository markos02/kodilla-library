package com.crud.kodillalibrary.library.controller;

import com.crud.kodillalibrary.library.domain.Reader;
import com.crud.kodillalibrary.library.domain.ReaderDto;
import com.crud.kodillalibrary.library.mapper.ReaderMapper;
import com.crud.kodillalibrary.library.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderDbService readerDbService;
    private final ReaderMapper readerMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers = readerDbService.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Integer readerId) throws ReaderNotFoundException {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(readerDbService.getReader(readerId)));
    }

    @PostMapping
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        readerDbService.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{readerId}")
    public ResponseEntity<Object> deleteReader(@PathVariable Integer readerId) throws ReaderNotFoundException {
        readerDbService.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }
}
