package com.crud.kodillalibrary.library.controller;

import com.crud.kodillalibrary.library.domain.*;
import com.crud.kodillalibrary.library.mapper.BookCopyMapper;
import com.crud.kodillalibrary.library.service.BookCopyDbService;
import com.crud.kodillalibrary.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/book_copies")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyDbService bookCopyDbService;
    private final BookCopyMapper bookCopyMapper;
    private final TitleDbService titleDbService;

    @GetMapping
    public ResponseEntity<List<GetBookCopyDto>> getBookCopies() {
        List<BookCopy> bookCopies = bookCopyDbService.getAllBookCopies();
        return ResponseEntity.ok(bookCopyMapper.mapToGetBookCopyDtoList(bookCopies));
    }

    @GetMapping(value = "{bookCopyId}")
    public ResponseEntity<GetBookCopyDto> getBookCopy(@PathVariable Integer bookCopyId) throws BookCopyNotFoundException {
        return ResponseEntity.ok(bookCopyMapper.mapToGetBookCopyDto(bookCopyDbService.getBookCopy(bookCopyId)));
    }

    @PostMapping
    public ResponseEntity<Void> createBookCopy(@RequestBody CreateBookCopyDto createBookCopyDto) throws TitleNotFoundException {
        BookCopy bookCopy = new BookCopy(createBookCopyDto.getStatus());
        bookCopy.setTitle(titleDbService.getTitle(createBookCopyDto.getTitleId()));
        bookCopyDbService.saveBookCopy(bookCopy);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{bookCopyId}")
    public ResponseEntity<Object> deleteBookCopy(@PathVariable Integer bookCopyId) throws BookCopyNotFoundException {
        bookCopyDbService.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BookCopyDto> changeStatus(@RequestBody BookCopyChangeStatusDto bookCopyChangeStatusDto) throws BookCopyNotFoundException {
        BookCopy savedBookCopy = bookCopyDbService.changeStatus(
                bookCopyChangeStatusDto.getId(),
                bookCopyChangeStatusDto.getStatus());
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDto(savedBookCopy));
    }
}
