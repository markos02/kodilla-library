package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentABook {
    private Title title;
    private Reader reader;
    private Date dateFrom;
    private Date dateTo;
}
