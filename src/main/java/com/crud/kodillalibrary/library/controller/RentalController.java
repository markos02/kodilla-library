package com.crud.kodillalibrary.library.controller;

import com.crud.kodillalibrary.library.domain.*;
import com.crud.kodillalibrary.library.mapper.RentalMapper;
import com.crud.kodillalibrary.library.service.BookCopyDbService;
import com.crud.kodillalibrary.library.service.ReaderDbService;
import com.crud.kodillalibrary.library.service.RentalDbService;
import com.crud.kodillalibrary.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library/rentals")
@RequiredArgsConstructor
public class RentalController {


    private final RentalDbService rentalDbService;
    private final RentalMapper rentalMapper;
    private final ReaderDbService readerDbService;
    private final BookCopyDbService bookCopyDbService;
    private final TitleDbService titleDbService;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals() {
        List<Rental> rentals = rentalDbService.getAllRentals();
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(rentals));
    }

    @GetMapping(value = "{rentalId}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Integer rentalId) throws RentalNotFoundException {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rentalDbService.getRental(rentalId)));
    }

    @PostMapping
    public ResponseEntity<Void> rentBook(@RequestBody RentABookDto rentABookDto) throws ReaderNotFoundException, BookCopyNotFoundException, TitleNotFoundException, BookNotAvailableException {
        Reader reader = readerDbService.getReader(rentABookDto.getReaderId());
        Title title = titleDbService.getTitle(rentABookDto.getTitleId());
        RentABook rentABook = new RentABook(title, reader, rentABookDto.getDateFrom(), rentABookDto.getDateTo());
        rentalDbService.rentABook(rentABook);
        return ResponseEntity.ok().build();
    }

}
