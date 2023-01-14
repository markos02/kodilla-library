package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentABookDto {
    private Integer titleId;
    private Integer readerId;
    private Date dateFrom;
    private Date dateTo;
}
