package com.crud.kodillalibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TitleDto {

    private Integer id;
    private String title;
    private String author;
    private int releaseDate;
    private List<BookCopy> bookCopies;
}

