package com.crud.kodillalibrary.library.domain;

import com.crud.kodillalibrary.library.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyChangeStatusDto {
    private Integer id;
    private Status status;
}
