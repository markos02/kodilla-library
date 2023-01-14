package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date created;
    private List<Rental> rentals;
}
