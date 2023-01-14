package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentalDto {
    private Integer id;
    private BookCopy bookCopy;
    private Reader reader;
    private Date dateFrom;
    private Date dateTo;
}
