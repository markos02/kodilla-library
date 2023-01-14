package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TitleNumberAvailableCopiesDto {
    private Integer id;
    private int numberAvailableCopies;
}
