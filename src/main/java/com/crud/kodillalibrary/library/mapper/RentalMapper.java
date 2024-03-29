package com.crud.kodillalibrary.library.mapper;

import com.crud.kodillalibrary.library.domain.Rental;
import com.crud.kodillalibrary.library.domain.RentalDto;
import com.crud.kodillalibrary.library.domain.RentalGetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalMapper {

    public Rental mapToRental(RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                rentalDto.getBookCopy(),
                rentalDto.getReader(),
                rentalDto.getDateFrom(),
                rentalDto.getDateTo()
        );
    }

    public RentalDto mapToRentalDto(Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getBookCopy(),
                rental.getReader(),
                rental.getDateFrom(),
                rental.getDateTo()
        );
    }

    public List<RentalDto> mapToRentalDtoList(List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentalDto)
                .toList();
    }

    public RentalGetDto mapToRentalGetDto(Rental rental) {
        return new RentalGetDto(
                rental.getId(),
                rental.getBookCopy().getId(),
                rental.getReader().getId(),
                rental.getDateFrom(),
                rental.getDateTo()
        );
    }

    public List<RentalGetDto> mapToRentalGetDtoList(List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentalGetDto)
                .toList();
    }
}
